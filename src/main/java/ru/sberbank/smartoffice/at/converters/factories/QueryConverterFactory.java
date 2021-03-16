package ru.sberbank.smartoffice.at.converters.factories;

import ru.sberbank.smartoffice.at.converters.QueryConverter;
import ru.sberbank.smartoffice.at.utilities.epochtime.EpochTime;

import java.util.Calendar;

public enum QueryConverterFactory implements QueryConverter {

    DateStartFrom {
        @Override
        public String convertValue(String value) {
            return Long.toString(EpochTime.day(value).atStart());
        }
    },
    UpdatedAtFrom {
        @Override
        public String convertValue(String value) {
            return Long.toString(EpochTime.day(value).atStart());
        }
    },
    UpdatedAtTo {
        @Override
        public String convertValue(String value) {
            return Long.toString(EpochTime.day(value).atEnd());
        }
    },
    DateFrom {
        @Override
        public String convertValue(String value) {
            return Long.toString(EpochTime.day(value).atStart());
        }
    },
    DateTo {
        @Override
        public String convertValue(String value) {
            return Long.toString(EpochTime.day(value).atEnd());
        }
    },
    DateStartTo {
        @Override
        public String convertValue(String value) {
            return Long.toString(EpochTime.day(value).atStart());
        }
    },
    Year {
        @Override
        public String convertValue(String value) {
            if (value.equalsIgnoreCase("текущий")) {
                return Integer.toString(java.time.Year.now().getValue());
            }
            return value;
        }
    },
    Month {
        @Override
        public String convertValue(String value) {
            if (value.equalsIgnoreCase("текущий")) {
                return Integer.toString(Calendar.getInstance().get(Calendar.MONTH) + 1);
            }
            return value;
        }
    }
}
