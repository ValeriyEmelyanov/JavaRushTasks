package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static Throwable CreateException(Enum en) {
        if (en != null) {
            String msg = en.name().charAt(0) + en.name().toLowerCase().replace('_', ' ').substring(1);

            if (en.getClass() == ApplicationExceptionMessage.class) {
                return new Exception(msg);
            }

            if (en.getClass() == DatabaseExceptionMessage.class) {
                return new RuntimeException(msg);
            }

            if (en.getClass() == UserExceptionMessage.class) {
                return new Error(msg);
            }
        }

        return new IllegalArgumentException();
    }
}
