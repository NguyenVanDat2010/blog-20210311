package com.kira.blog.constant;

public interface BlogConst {
    int MIN_AGE = 18;

    String FORMAT_BIRTH_DATE_APPLICANT = "yyMMdd";

    /**
     * format X{n}
     * right: 11, 3333, 4444
     * error: 12,3334,3334442
     */
    String REGEX_ALL_SAME_NUMBER = "(\\d)\\1+";

    String FORMAT_REQUEST_BIRTH_DATE = "yyyyMMdd";

    //Good: 0967838708
    String REGEX_FORMAT_PHONE = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";

    String REGEX_FORMAT_REQUEST_BIRTH_DATE = "([1-9][0-9]{3}-)((0[1-9])|(1[0-2])-)((0[1-9])|([1,2][0-9])|([3][0,1]))";

    String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    String REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

    String REGEX_USERNAME = "^(?=.{3,15}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

    String REGEX_BASE64_IMAGE = "data:image\\/(jpg|png|jpeg|bmp);base64,([^\\\"]*)";

    String PARAM_GENDER = "Male, Female, Other";

    //Ex: ROLE_USER
    String REGEX_ROLE = "^([ROLE]_)[A-Z]+(\\S[^\\W])([\\w]{1,10})$";

    //Resend up to 2 times in an interval of 30 seconds after
    int TIME_INTERVAL_RESEND = 30;

}
