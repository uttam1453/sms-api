/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appster.sms.api.common.serializer.dateSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Dheeraj
 */
public class DateSerializers {

    public static final String DATE_FORMAT_MDYHMS = "MM-dd-yyyy HH:mm:ss";
    public static final String DATE_FORMAT_MDY = "MM-dd-yyyy";
    public static final Logger logger = LoggerFactory.getLogger(DateSerializers.class);
    public static final String DATE_FORMAT_MDY_SLASH_SEPERATED = "MM/dd/yyyy";
    private DateSerializers() {
    }

    @Component
    public static class StandardDateSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            Format formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String s = formatter.format(date);
            gen.writeString(s);
        }
    }

    @Component
    public static class MDYHMS extends JsonSerializer<Date> {

        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            Format formatter = new SimpleDateFormat(DATE_FORMAT_MDYHMS);
            String s = formatter.format(date);
            gen.writeString(s);
        }
    }

    @Component
    public static class MDYHMS_SLASH_SEPERATED extends JsonSerializer<Date> {

        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            Format formatter = new SimpleDateFormat(DATE_FORMAT_MDY_SLASH_SEPERATED);
            String s = formatter.format(date);
            gen.writeString(s);
        }
    }

    @Component
    public static class FullDateSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            Format formatter = new SimpleDateFormat("dd MMMM, yyyy");
            String s = formatter.format(date);
            gen.writeString(s);
        }
    }

    @Component
    public static class OfficeTimeSerializer extends JsonSerializer<Time> {

        @Override
        public void serialize(Time time, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            Format formatter = new SimpleDateFormat("HH:mm");
            String s = formatter.format(time);
            gen.writeString(s);
        }
    }

    @Component
    public static class ISO_8601_Serializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date time, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            String s = formatter.format(time);
            gen.writeString(s);
        }
    }

    @Component
    public static class TimeRecencySerializer extends JsonSerializer<Date> {

        public static final Logger logger = LoggerFactory.getLogger(TimeRecencySerializer.class);

        @Override
        public void serialize(Date time, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            long currentTimeInMills = System.currentTimeMillis();
            long inputTimeInMills = time.getTime();

            long diffMilliSeconds = currentTimeInMills - inputTimeInMills;
            long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diffMilliSeconds);
            long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diffMilliSeconds);
            long diffHours = TimeUnit.MILLISECONDS.toHours(diffMilliSeconds);
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffMilliSeconds);
            long diffInMonth = diffInDays / 30;

            String difference;
            if (diffInMonth >= 1) {
                difference = diffInMonth + " months";
            } else if (diffInDays >= 1) {
                difference = diffInDays + " days";
            } else if (diffHours >= 1) {
                difference = diffHours + " hours";
            } else if (diffMinutes >= 1) {
                difference = diffMinutes + " minutes";
            } else {
                difference = diffSeconds + " seconds";
            }
            gen.writeString(difference + " ago");
        }
    }
}
