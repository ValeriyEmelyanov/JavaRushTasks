package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private List<LogEntry> logList;
    private SimpleDateFormat sdf = new SimpleDateFormat("d.M.y H:m:s");

    public LogParser(Path logDir) {
        logList = readLogs(logDir);
    }

    /////////////////////////////////////////////////////
    // IPQuery

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getLogEntryStream(after, before)
                .map(e -> e.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.user != null && e.user.equals(user))
                .map(e -> e.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.event != null && e.event.equals(event))
                .map(e -> e.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.status != null && e.status.equals(status))
                .map(e -> e.ip)
                .collect(Collectors.toSet());
    }

    /////////////////////////////////////////////////////
    // Common

    private Stream<LogEntry> getLogEntryStream(Date after, Date before) {
        return logList.stream()
                .filter(e -> e != null && e.date != null
                        && (after == null || e.date.after(after) || e.date.equals(after))
                        && (before == null || e.date.before(before) || e.date.equals(before)));
    }

    /////////////////////////////////////////////////////
    // Read log files

    private List<LogEntry> readLogs(Path logDir) {
        List<File> fileList = getLogFiles(logDir.toFile());

        List<LogEntry> logEntries = new ArrayList<>();

        for (File file : fileList) {
            logEntries.addAll(readLogFile(file));
        }

        return logEntries;
    }

    private List<File> getLogFiles(File file) {
        List<File> fileList = new ArrayList<>();

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    // если надо посмотреть вложенные папки
                    //fileList.addAll(getLogFiles(f));
                    // а если нет то так
                    addFileToList(f, fileList);
                }
            } else {
                addFileToList(file, fileList);
            }
        }

        return fileList;
    }

    private void addFileToList(File file, List<File> fileList) {
        if (file.isFile() && file.exists() && file.getName().toLowerCase().endsWith(".log")) {
            fileList.add(file);
        }
    }

    private List<LogEntry> readLogFile(File file) {
        List<LogEntry> logEntries = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));) {
            while (reader.ready()) {
                LogEntry logEntry = new LogEntry(reader.readLine());
                if (logEntry != null) {
                    logEntries.add(logEntry);
                }
            }
        } catch (IOException ignored) {
        }
        return logEntries;
    }

    /////////////////////////////////////////////////////
    // UserQuery

    @Override
    public Set<String> getAllUsers() {
        return logList.stream()
                .map(e -> e.user)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getLogEntryStream(after, before)
                .map(e -> e.user)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.user != null && e.user.equals(user))
                .map(e -> e.event)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.ip != null && e.ip.equals(ip))
                .map(e -> e.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUsersWithEvent(Event.LOGIN, after, before);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUsersWithEvent(Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUsersWithEvent(Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUsersWithEvent(Event.SOLVE_TASK, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getUsersWithEvent(Event.SOLVE_TASK, task, after, before);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUsersWithEvent(Event.DONE_TASK, after, before);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getUsersWithEvent(Event.DONE_TASK, task, after, before);
    }

    private Set<String> getUsersWithEvent(Event event, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.event == event)
                .map(e -> e.user)
                .collect(Collectors.toSet());
    }

    private Set<String> getUsersWithEvent(Event event, Integer task, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.event == event && e.task != null && e.task.equals(task))
                .map(e -> e.user)
                .collect(Collectors.toSet());
    }

    /////////////////////////////////////////////////////
    // DateQuery

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.user != null && e.user.equals(user) && e.event == event)
                .map(e -> e.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getDatesWithStatus(Status.FAILED, after, before);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getDatesWithStatus(Status.ERROR, after, before);
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return getDatesWithEvent(user, Event.LOGIN, after, before)
                .stream()
                .min(Date::compareTo)
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return getDatesWithEvent(user, Event.SOLVE_TASK, task, after, before)
                .stream()
                .min(Date::compareTo)
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return getDatesWithEvent(user, Event.DONE_TASK, task, after, before)
                .stream()
                .min(Date::compareTo)
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getDatesWithEvent(user, Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getDatesWithEvent(user, Event.DOWNLOAD_PLUGIN, after, before);
    }

    private Set<Date> getDatesWithStatus(Status status, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.status == status)
                .map(e -> e.date)
                .collect(Collectors.toSet());
    }

    private Set<Date> getDatesWithEvent(String user, Event event, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.user != null && e.user.equals(user)
                        && e.event == event)
                .map(e -> e.date)
                .collect(Collectors.toSet());
    }

    private Set<Date> getDatesWithEvent(String user, Event event, Integer task, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.user != null && e.user.equals(user)
                        && e.event == event && e.task != null && e.task.equals(task))
                .map(e -> e.date)
                .collect(Collectors.toSet());
    }

    /////////////////////////////////////////////////////
    // EventQuery

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getLogEntryStream(after, before)
                .map(e -> e.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.ip != null && e.ip.equals(ip))
                .map(e -> e.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.user != null && e.user.equals(user))
                .map(e -> e.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getEventsForStatus(Status.FAILED, after, before);
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getEventsForStatus(Status.ERROR, after, before);
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getLogEntryStream(after, before)
                .filter(e -> e.event == Event.SOLVE_TASK && e.task != null && e.task.equals(task))
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getLogEntryStream(after, before)
                .filter(e -> e.event == Event.DONE_TASK && e.task != null && e.task.equals(task))
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        getLogEntryStream(after, before)
                .filter(e -> e.event == Event.SOLVE_TASK)
                .forEach(e -> result.put(e.task, result.getOrDefault(e.task, 0) + 1));
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        getLogEntryStream(after, before)
                .filter(e -> e.event == Event.DONE_TASK)
                .forEach(e -> result.put(e.task, result.getOrDefault(e.task, 0) + 1));
        return result;
    }

    private Set<Event> getEventsForStatus(Status status, Date after, Date before) {
        return getLogEntryStream(after, before)
                .filter(e -> e.status == status)
                .map(e -> e.event)
                .collect(Collectors.toSet());
    }

    /////////////////////////////////////////////////////
    // QLQuery

    @Override
    public Set<Object> execute(String query) {
        if (query == null) {
            return null;
        }

        String[] words = query.split(" ");

        if (words.length == 2 && words[0].toLowerCase().equals("get")) {
            return executeSimpleQuery(words[1]);
        }

        if (words.length > 2) {
            return executeFullQuery(query);
        }

        return null;
    }

    private Set<Object> executeFullQuery(String query) {
        Pattern pattern = Pattern.compile("get (?<tag>\\w+)" +
                "(\\sfor\\s(?<field>\\w+)\\s=\\s\"(?<value>.+?)\")?" +
                "(\\sand date between\\s\"(?<after>[\\d]+.[\\d]+.[\\d]+ [\\d]+:[\\d]+:[\\d]+)\"\\sand\\s\"(?<before>[\\d]+.[\\d]+.[\\d]+ [\\d]+:[\\d]+:[\\d]+)\")?");
        Matcher matcher = pattern.matcher(query);

        if (!matcher.find()) {
            return null;
        }

        String tag = matcher.group("tag");
        String field = matcher.group("field");
        String value = matcher.group("value");
        String after = matcher.group("after");
        String before = matcher.group("before");

        if (tag == null || field == null || value == null) {
            return null;
        }

        // for field2 = "value1"
        Stream<LogEntry> stream = null;
        switch (field.toLowerCase()) {
            case "date":
                Date dateValue;
                try {
                    dateValue = sdf.parse(value);
                } catch (ParseException e) {
                    return null;
                }
                stream = logList.stream()
                        .filter(e -> dateValue.equals(getFieldValue(field, e)));
                break;
            case "event":
                Event eventValue = Event.valueOf(value);
                stream = logList.stream()
                        .filter(e -> eventValue == getFieldValue(field, e));
                break;
            case "status":
                Status statusValue = Status.valueOf(value);
                stream = logList.stream()
                        .filter(e -> statusValue == getFieldValue(field, e));
                break;
            default:
                stream = logList.stream()
                        .filter(e -> value.equals(getFieldValue(field, e)));
        }

        // and date between "after" and "before"
        if (after != null && before != null) {
            try {
                Date dateAfter = sdf.parse(after);
                Date dateBefore = sdf.parse(before);
                stream = stream
                        .filter(e -> e.date != null
                                && e.date.after(dateAfter)
                                && e.date.before(dateBefore)
//                                && (e.date.after(dateAfter) || e.date.equals(dateAfter))
//                                && (e.date.before(dateBefore) || e.date.equals(dateBefore))
                        );
            } catch (ParseException e) {
                return null;
            }
        }

        // get field1
        return stream
                .map(e -> getFieldValue(tag, e))
                .collect(Collectors.toSet());
    }

    private Set<Object> executeSimpleQuery(String fieldName) {
        return logList.stream()
                .map(e -> getFieldValue(fieldName, e))
                .collect(Collectors.toSet());
    }

    private Object getFieldValue(String fieldName, Object instance) {
        try {
            return LogEntry.class.getDeclaredField(fieldName).get(instance);
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
            return null;
        }
    }

    /////////////////////////////////////////////////////
    // class LogEntry

    private class LogEntry {
        String ip;
        String user;
        Date date;
        Event event;
        Integer task;
        Status status;

        LogEntry(String line) {
            String[] values = line.split("\t");
            // ip
            ip = values[0];
            // user
            user = values[1];
            // date
            try {
                date = sdf.parse(values[2]);
            } catch (ParseException e) {
                date = null;
            }
            // event
            String[] eventDesc = values[3].split(" ");
            event = Event.valueOf(eventDesc[0].toUpperCase());
            // task
            if (event == Event.SOLVE_TASK || event == Event.DONE_TASK) {
                task = Integer.parseInt(eventDesc[1]);
            } else {
                task = null;
            }
            // status
            status = Status.valueOf(values[4].toUpperCase());
        }
    }
}