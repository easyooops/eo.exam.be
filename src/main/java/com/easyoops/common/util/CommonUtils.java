package com.easyoops.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? Collections.<T>emptyList() : iterable;
    }

    public static String encryptData(String encryptData) {

        String base = encryptData;

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }

    }

    public static String getRandomString() {

        char[] pwCollectionAll = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};
        char[] pwCollectionSpCha = new char[]{'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};
        char[] pwCollectionNum = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',};
        StringBuilder ranPw = new StringBuilder();
        StringBuilder ranPwNum = new StringBuilder();
        StringBuilder ranPwSpCha = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < 1; i++) {
            int selectRandomPw = random.nextInt(pwCollectionNum.length);
            ranPwNum.append(pwCollectionNum[selectRandomPw]);
        }
        for (int i = 0; i < 1; i++) {
            int selectRandomPw = random.nextInt(pwCollectionSpCha.length);
            ranPwSpCha.append(pwCollectionSpCha[selectRandomPw]);
        }
        for (int i = 0; i < 8; i++) {
            int selectRandomPw = random.nextInt(pwCollectionAll.length);
            ranPw.append(pwCollectionAll[selectRandomPw]);
        }
        return ranPwSpCha.toString() + ranPw.toString() + ranPwNum.toString();
    }

    public static String getRealIp(HttpServletRequest request) {
        List<String> checkHeaderNames = new ArrayList<>(
                Arrays.asList("x-forwarded-for", "x-forward-for", "http_x_forwarded_for", "x_forwarded_for",
                        "http_client_ip", "wl-proxy-client-ip", "proxy-client-ip", "remote_addr", "x-real-ip"));
        String requestIp = "";

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {

            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            if (checkHeaderNames.contains(headerName.toLowerCase())) {
                requestIp = headerValue;
            }
        }

        if (!StringUtils.isEmpty(requestIp) && requestIp.indexOf(',') > -1) {
            requestIp = requestIp.split(",")[0].trim();
        }

        if (StringUtils.isEmpty(requestIp)) {
            requestIp = request.getRemoteAddr();
        }

        return requestIp;
    }

    public static boolean isEmptyText(String str) {
        // null 인 경우 return true
        if (str == null) {
            return true;
        }
        // 공백 제거
        str = str.replace("\\p{Z}", ""); // 띄어쓰기 공백 제거
        str = str.replace("\\p{Z}", ""); // ㄱ + 한자, 1번 공백 제거
        // 공백 제거 후 length 가 1보다 작으면 return true
        return str.length() < 1;
    }


    public static String toNotNull(String value, String defaultValue) {
        String returnValue;
        if (value == null || value.trim().equals("") || value.trim().equals("null")) {
            returnValue = defaultValue;
        } else {
            returnValue = value;
        }
        return returnValue;
    }

    public static boolean isEmpty(Object obj) {

        if (obj == null)
            return true;

        if ((obj instanceof String) && (((String) obj).trim().length() == 0)) {
            return true;
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof List) {
            return ((List<?>) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            return (((Object[]) obj).length == 0);
        }

        return false;

    }

    public static String getCurrentDate() {
        Date dateNow = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST")).getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return formatter.format(dateNow);

    }

    public static String getCurrentDateYMD() {
        Date dateNow = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST")).getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        return formatter.format(dateNow);

    }

    public static String getCurrentDateYMDHypen() {
        Date dateNow = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST")).getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return formatter.format(dateNow);

    }

    public static String getCurrentMonth() {
        Date dateNow = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST")).getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.getDefault());
        return formatter.format(dateNow);

    }

    public static String getMonthAgoMonth(int n) {
        Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
        cal.add(Calendar.MONTH, n); // 한달전 날짜 가져오기
        Date monthago = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM", Locale.getDefault());
        return formatter.format(monthago);
    }

    public static String getAgoDate(int n) {
        Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
        cal.add(Calendar.DATE, n); // 한달전 날짜 가져오기
        Date monthago = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return formatter.format(monthago);
    }

    public static String getCurrentDateYYYMMD() {
        // 2020-05-11 11:16:03
        Date dateNow = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST")).getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return formatter.format(dateNow);

    }

    public static String getMonthAgoDate(int n) {
        Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
        cal.add(Calendar.MONTH, n); // 한달전 날짜 가져오기
        Date monthago = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.getDefault());
        return formatter.format(monthago);
    }

    public static long getCurrentDateTimeStamp() throws ParseException {
        Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
        cal.add(Calendar.MINUTE, -30); // 한달전 날짜 가져오기
        Date tenMinutsAgoe = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String date = formatter.format(tenMinutsAgoe);
        Date parsedTimeStamp = formatter.parse(date);
        Timestamp timestamp1 = new Timestamp(parsedTimeStamp.getTime());
        long timestamp = timestamp1.getTime();

        return timestamp;
    }

    public static String replace(String src, String from, String to) {
        if (src == null) {
            return null;
        }
        if (from == null) {
            return src;
        }
        if (to == null) {
            to = "";
        }

        StringBuilder buf = new StringBuilder();

        for (int pos; (pos = src.indexOf(from)) >= 0; ) {
            buf.append(src.substring(0, pos));
            buf.append(to);
            src = src.substring(pos + from.length());
        }

        buf.append(src);
        return buf.toString();
    }

    public static String reverseXmlString(String strText) {
        if (strText == null) {
            strText = "";
        } else {
            strText = strText.trim();

            strText = replace(strText, "&amp;", "&");
            strText = replace(strText, "&lt;", "<");
            strText = replace(strText, "&gt;", ">");
            strText = replace(strText, "&apos;", "'");
            strText = replace(strText, "&quot;", "\"");
        }

        return strText;
    }

    public static boolean byteCheck(String txt, int standardByte) {

        // 바이트 체크 (영문 1, 한글 2, 특문 1)
        int en = 0;
        int ko = 0;
        int etc = 0;
        boolean result = true;
        char[] txtChar = txt.toCharArray();
        for (int j = 0; j < txtChar.length; j++) {
            if (txtChar[j] >= 'A' && txtChar[j] <= 'z') {
                en++;
            } else if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
                ko++;
                ko++;
            } else {
                etc++;
            }
        }

        int txtByte = en + ko + etc;
        if (txtByte > standardByte) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    public static String nvl(String str, String rep) {
        return str==null ? rep : str;
    }

    public static boolean isEmail(String value) {
        Pattern p = Pattern.compile("^((\\w|[\\-\\.\\+])+)@((\\w|[\\-\\.])+)\\.([A-Za-z]+)$");
        Matcher m = p.matcher(value);
        if ( m.matches() ) {
            return false;
        }
        return true;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : "_" 키워드로 연결된 문자열을 카멜표기법으로 변환 하는 매서드
     * */
    public static String convertFromUnderBarToCamel(String str){

        String result = "";
        str = str.toLowerCase();

        for ( int i=0; i<str.length(); i++ ) {

            String tmp = String.valueOf(str.charAt(i));

            if ( "_".equals(tmp) ) {
                tmp = String.valueOf(str.charAt(i+1)).toUpperCase();
                result += tmp;
                i++;
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 각 라인별 탬을 추가 하는 매서드
     * */
    public static String addTab(String str){

        str = "\t" + str.replaceAll("\n", "\n\t");
        return str;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 각 라인별 탬을 추가 하는 매서드
     * */
    public static String removeTab(String str){

        if ( str.indexOf("\t") == 0 ) str = str.substring(1);
        str = str.replaceAll("\n\t", "\n");
        return str;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인  경우 공백 문자로 변경 하는 매서드 [OBJECT]
     * */
    public static String isNull(Object obj){

        if ( obj == null ) return "";
        return isNull(obj.toString());
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 공백 문자로 변경 하는 매서드 [STRING]
     * */
    public static String isNull(String str){
        return isNull(str, "");
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 CALLBACK 데이터로 반환하는 매서드 [STRING]
     * */
    public static String isNull(String c, String str){
        return isNullObj(c, str).toString();
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 CALLBACK 데이터로 반환하는 매서드  [INTEGER]
     * */
    public static int isNull(int c, int val){
        return Integer.parseInt(isNullObj(c, val).toString());
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 CALLBACK 데이터로 반환하는 매서드  [DOUBLE]
     * */
    public static double isNull(double c, double val){
        return Double.parseDouble(isNullObj(c, val).toString());
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 CALLBACK 데이터로 반환하는 매서드  [LONG]
     * */
    public static long isNull(long c, long val){
        return Long.parseLong(isNullObj(c, val).toString());
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 CALLBACK 데이터로 반환하는 매서드  [OBJECT]
     * */
    public static Object isNullObj(Object obj, Object val){
        return ( obj == null ? val : obj);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 0을 반환하는 매서드
     * */
    public static int isNull(int val){
        return isNull(val, 0);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 0을 반환하는 매서드  [DOUBLE]
     * */
    public static double isNull(double val){
        return isNull(val, 0);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 0을 반환하는 매서드  [LONG]
     * */
    public static long isNull(long val){
        return isNull(val, 0);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 데이터가 NULL인 경우 날짜형식의 데이터를 공백 문자열로 변환 하는 매서드
     * */
    public static String isNull(Date val){
        return isNullObj(val, "").toString();
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : OBJECT TYPE 데이터를 INTEGER TYPE 데이터로 변환하는 매서드
     * */
    public static int parseInt(Object obj){
        int result = 0;
        try{
            result = Integer.parseInt(obj.toString());
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : OBJECT TYPE 데이터를 LONG TYPE 데이터로 변환하는 매서드
     * */
    public static long parseLong(Object obj){
        long result = 0;
        try{
            result = Long.parseLong(obj.toString());
        }catch(Exception e){
            result = 0;
        }
        return result;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 배열 데이터를 "," 구분자로 데이터 재 정열하는 매서드
     * */
    public static String serializeString(String [] arr){
        return serializeString(arr, ",");
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 배열 데이터를 지정하는 구분자로 데이터 재 정열하는 매서드
     * */
    public static String serializeString(String [] arr, String token){
        if ( arr == null)
            return "";

        String result = "";
        for ( int i=0; i<arr.length; i++ ){
            if ( !result.equals("") ){
                result += token;
            }
            result += arr[i];
        }
        return result;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 주어진 문자열에 포함된 태그를 삭제하여 반환하는 매서드
     * */
    public static String removeTag(String str){
        if ( str == null ){
            return "";
        }
        String regex = "<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9]*=[^>]*)?(\\s)*(/)?>";
        String result = str.replaceAll(regex, "");
        return result;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 주어진 문자열에 포함된 태그를 삭제하고, 지정된 BYTE만큼 잘라서 반환하는 매서드
     * */
    public static String removeTag(String str, Integer cutByte, String addStr){

        String result = removeTag(str);

        return cutString(result, cutByte, addStr);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 주어진 문자열에 포함된 태그를 삭제하고, 지정된 BYTE만큼 잘라서 반환 및 추가 문자열을 변환하는 매서드
     * */
    public static String removeTag2(String str, Integer cutByte, String addStr){

        String result = removeTag(str);

        result = result.replaceAll("&nbsp;", " ");

        return cutString(result, cutByte, addStr);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 주어진 문자열에 포함된 태그를 삭제하고, 지정된 BYTE만큼 잘라서 반환 및 추가 문자열을 변환하는 매서드
     * */
    public static String removeTagAndFlat(String str, Integer cutByte, String addStr){

        String result = removeTag(str);

        result = result.replaceAll("&nbsp;", " ");
        result = result.replaceAll("\t", "");
        result = result.replaceAll("\r\n", " ");		// 공백 처리.

        return cutString(result, cutByte, addStr);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 지정한 문자열로  구분하여 문자열 반환하는 매서드
     * */
    public static String getSplitIndexStr(String targetStr, String tokenStr, Integer index){
        String result = "";

        if ( targetStr == null )
            return "";

        if ( targetStr.indexOf(tokenStr) == -1 )
            return "";

        String [] arr = targetStr.split(tokenStr);
        if ( arr.length > index )
            return arr[index];

        return result;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 공백문자열을 NULL 데이터로 반환하는 매서드
     * */
    public static String rsNull(String str) {
        return (str == "" ? null : str);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 바이트 단위로 문자열을 자르는 매서드
     * */
    public static String cutString(String szText, Integer nLength, String addSt)
    {
        if(szText == null || "".equals(szText))
        {
            return szText;
        }
        String r_val = szText;
        int oF = 0, oL = 0, rF = 0, rL = 0;
        int nLengthPrev = 0;
        try {
            byte[] bytes = r_val.getBytes("UTF-8"); // 바이트로 보관
            // x부터 y길이만큼 잘라낸다. 한글안깨지게.
            if(bytes.length <= nLength)
            {
                return r_val;
            }
            int j = 0;
            if (nLengthPrev > 0)
            {
                while (j < bytes.length) {
                    if ((bytes[j] & 0x80) != 0) {
                        oF += 2;
                        rF += 3;
                        if (oF + 2 > nLengthPrev) {
                            break;
                        }
                        j += 3;
                    } else {
                        if (oF + 1 > nLengthPrev) {
                            break;
                        }
                        ++oF;
                        ++rF;
                        ++j;
                    }
                }
            }
            j = rF;
            while (j < bytes.length) {
                if ((bytes[j] & 0x80) != 0) {
                    if (oL + 2 > nLength) {
                        break;
                    }
                    oL += 2;
                    rL += 3;
                    j += 3;
                } else {
                    if (oL + 1 > nLength) {
                        break;
                    }
                    ++oL;
                    ++rL;
                    ++j;
                }
            }
            if(addSt != null && !"".equals(addSt))
            {
                r_val = new String(bytes, rF, rL, "UTF-8") + addSt; // ... 추가	
            }
            else
            {
                r_val = new String(bytes, rF, rL, "UTF-8"); //	
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return r_val;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 이미지 태그를 검색하여 SRC경로를 추출하는 매서드
     * */
    public static List<String> getSrcFromImageTag(String str) {

        if ( str == null )
            return new ArrayList<String>();

        if ( "".equals(str) )
            return new ArrayList<String>();

        Pattern nonValidPattern = Pattern
                .compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

        List<String> result = new ArrayList<String>();
        Matcher matcher = nonValidPattern.matcher(str);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 이미지 태그를 검색하여 IMG를 추출하는 매서드
     * */
    public static String getImageTag(String str) {

        if ( str == null )
            return "";

        Pattern nonValidPattern = Pattern
                .compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

        Matcher matcher = nonValidPattern.matcher(str);
        String imgTag = "" ;
        if (matcher.find()) {
            imgTag = matcher.group(0);
        }
        return imgTag;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 문자열 태그 내 URL 경로를 추출하는 매서드
     * */
    public static String getSrcFromTag(String str) {

        if ( str == null )
            return "";

        Pattern pattern = Pattern.compile("[/s]*=[\"']?([^>\"']+)[\"']?[^>]*");

        String imgTag = "";
        Matcher matcher = pattern.matcher(str);
        imgTag = matcher.group(0);

        return imgTag;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 입력된 숫자를받아 천단위 콤마를 추가 하여 반환하는 매서드 [INTEGER]
     * */
    public static String getThousandsCommas(Integer num) {
        return new DecimalFormat("#,###").format(num);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 입력된 숫자를받아 천단위 콤마를 추가 하여 반환하는 매서드 [LONG]
     * */
    public static String getThousandsCommas2(Long num) {
        return new DecimalFormat("#,###").format(num);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 입력된 숫자를받아 천단위 콤마를 제거하여 반환하는 매서드
     * */
    public static int getUnCommas(String num) {
        return Integer.parseInt(num.replaceAll("\\,", ""));
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 입력된 숫자를받아 천단위 콤마를 제거하여 반환하는 매서드
     * */
    public static String getUnCommasString(String num) {
        return num.replaceAll("\\,", "");
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 랜덤 숫자 반환하여 길이에 맞는 숫자를 생성하는 매서드
     * */
    public static String makeRandomNum(int length) {
        String result = "";
        Random random = new Random();
        for (int i=0;i<length;i++){
            int idx = random.nextInt(10);
            result += "" + String.valueOf(idx);
        }
        return result;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 길이에 맞는 문자열을 조합하여 비밀번호를 생성하는 메서드
     * */
    public static String makeRandomPassword(int length){
        String tmp = "1234567890abcdefghijklmnopqrstuvwxyz1234567890!@#$%^*()ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$1234567890!@#$%^*()";

        String result = "";
        Random random = new Random();
        for (int i=0;i<length;i++){
            int idx = random.nextInt(100);
            result += tmp.substring(idx, idx+1);
        }
        return result;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 랜덤키를 생성하는 매서드 [숫자+알파멧 조합]
     * */
    public static String makeRandomKey(int len) throws IOException
    {
        char[] charSet = new char[]{'0','1','2','3','4','5','6','7','8','9'
                ,'a','b','c','d','e','f','g','h','i','j','k','l','m'
                ,'n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int idx = 0;
        StringBuffer sb = new StringBuffer();

        for(int i=0 ; i < len; i++)
        {
            idx = (int)(charSet.length*Math.random());
            sb.append(charSet[idx]);
        }
        return sb.toString();
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 이메일 유효성을 체크하는 매서드
     * */
    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 문자열 길이를 반환하는 매서드 [영문,한글,기타]
     * */
    public static int getStrLength(String str){
        int en = 0;
        int ko = 0;
        int etc = 0;

        char[] string = str.toCharArray();

        for(int j=0; j<string.length; j++) {
            if(string[j]>='A' && string[j]<='z') {
                en++;
            }
            else if(string[j]>='\uAC00' && string[j]<='\uD7A3') {
                ko++;
                ko++;
            }
            else {
                etc++;
            }
        }

        return (en + ko + etc);
    }

    /*
     * @AUTHOR   : EasyOops
     * @DATE     : 2022.05.
     * @DESCRIPT : 문자열 지정된 문자열 길이를 반환하는 매서드
     * */
    public static int getPetternStrCount(String str, String petternStr){
        int count = 0;
        Pattern p = Pattern.compile(petternStr);
        Matcher m = p.matcher(str);
        for(int i = 0 ; m.find(i); i= m.end())
        {
            count++;
        }
        return count;
    }    
}
