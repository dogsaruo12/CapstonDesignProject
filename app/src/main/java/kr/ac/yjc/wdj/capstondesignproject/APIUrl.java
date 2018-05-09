package kr.ac.yjc.wdj.capstondesignproject;

/**
 * APIUrl 클래스
 *
 * @author devetude
 */
public class APIUrl {
    /**
     * api 서버 url
     *
     * 주의) Retrofit2 부터 base url의 끝에 /(루트)를 꼭 기입해줘야 함
     */
    public static final String API_BASE_URL = "http://13.124.213.132/";

    /**
     * 실제 api 경로
     *
     * 주의) /sign/in.json (x), sign/in.json (o)
     */
    // 회원가입
    public static final String SIGN_UP_URL = "sign/up.json";

    // 로그인
    public static final String LOGIN_URL = "login";

    // 개인정보 조회
    public static final String INFO_URL = "professor/info/select";

    // 담당강의 목록 조회
    public static final String SUBJECT_LIST_URL = "professor/subject/list";

    // 지도학생 목록 조회
    public static final String STUDENT_LIST = "tutor/class/student_list";

    // 지도학생 오늘 출결 조회
    public static final String ATTEND_LIST = "tutor/attendance/today";


    public static final String ANALYSIS_URL = "student/mobile/attendance";
}