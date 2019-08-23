package com.siifi.infos.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.xjzx.study.deaprj.vo.CurrencyVo;
import com.xjzx.study.planexam.vo.ExamSubjectCatVo;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s){
		if(null == s) {
			return true;
		}
		if(s.trim().length() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否不为空
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s){
		if(null != s && s.trim().length()>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 在字符串s1中排除字符串s2
	 * @param s1
	 * @param s2
	 */
	public static String excludeStr(String s1, String s2){
		if(s1.indexOf(s2)==-1) return s1;
		return new StringBuilder().append(s1.substring(0, s1.indexOf(s2))).append(s1.substring(s1.indexOf(s2)+s2.length()+1)).toString();
	}
	
	/**
	 * 截去字符串最后一位
	 * @param strb
	 * @return
	 */
	public static String trimLastBit(StringBuilder strb){
		if(null!=strb && strb.length()>0){
			return strb.deleteCharAt(strb.length()-1).toString();
		}
		return "";
	}

	/**
	 * 生成随机数
	 * @param str
	 * @param strLen
	 * @param length
	 * @return
	 */
	public static String getRandomString(String str, int strLen, int length){
		Random random=new Random();
		StringBuffer sf=new StringBuffer();
		for(int i=0;i<length;i++){
		   int number=random.nextInt(strLen);//0~61
		   sf.append(str.charAt(number));
		}
		return sf.toString();
	}
	
	/**
	 * 生成由字母和数字组成的随机数
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length){
		String str="abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		return getRandomString(str, str.length(), length);
	}
	
	/**
	 * 生成数字组成的随机数
	 * @param length
	 * @return
	 */
	public static String getRandomNumString(int length){
		String str="0123456789";
		return getRandomString(str, str.length(), length);
	}
	
	/**
	 * 字符s2在字符s1中出现的次数
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int frequency(String s1, String s2){
		int count = 0;
		int m = s1.indexOf(s2);

		while (m != -1) {
		    m = s1.indexOf(s2, m + 1);
		    count++;
		}
		return count;
	}
	
	/**
	 * 获得参加来源
	 * @param origin
	 * @return
	 */
	public static String getOrigin(String origin){
		switch(origin){
		case "0":
			return "app";
		case "1":
			return "mail";
		case "2":
			return "微信";
		case "3":
			return "微博";
		case "4":
			return "Web";
		}
		return "";
	}
	
	/**
	 * 获得学员级别
	 * @param level
	 * @return
	 */
	public static String getStudentLevel(int level){
		switch(level){
		case 1:
			return "省部级";
		case 2:
			return "厅局级";
		case 3: 
			return "县处级";
		case 4:
			return "科级";
		case 5:
			return "普通党员或干部";
		case 100:
			return "其他";
		}
		return "";
	}
	/**
	 * 功能：获得学员级别
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static Integer getStudentLevel(String value){
		Integer level = -1;
		if("省部级".equalsIgnoreCase(value)){
			level = 1;
		}else if("厅局级".equalsIgnoreCase(value)){
			level = 2;
		}else if("县处级".equalsIgnoreCase(value)){
			level = 3;
		}else if("科级".equalsIgnoreCase(value)){
			level = 4;
		}else if("普通党员或干部".equalsIgnoreCase(value)){
			level = 5;
		}else if("其他".equalsIgnoreCase(value)){
			level = 100;
		}else{
			level = -1;
		}
		return level;
	}
	/**
	 * 功能：获得试题类型
	 * @param pattern
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-12
	 * @modified:Liubaofeng
	 * @modified date:2013-3-12
	 */
	public static String getExamPaperPattern(int pattern){
		switch(pattern){
		case 1:
			return "单选题";
		case 2:
			return "多选题";
		case 3: 
			return "判断题";
		case 4:
			return "填空题";
		case 5:
			return "计算题";
		case 6:
			return "问答题";
		}
		return "";
	}
	/**
	 * 功能：获得学员学历
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static Integer getHighEdu(String value){
		Integer result = -1;
		if("小学及以下".equalsIgnoreCase(value)){
			result = 1;
		}else if("初中".equalsIgnoreCase(value)){
			result = 2;
		}else if("高中".equalsIgnoreCase(value)){
			result = 3;
		}else if("中专".equalsIgnoreCase(value)){
			result = 4;
		}else if("大专".equalsIgnoreCase(value)){
			result = 5;
		}else if("本科".equalsIgnoreCase(value)){
			result = 6;
		}else if("研究生".equalsIgnoreCase(value)){
			result = 7;
		}else if("博士及以上".equalsIgnoreCase(value)){
			result = 8;
		}else{
			result = -1;
		}
		return result;
	}
	/**
	 * 功能：获得学员学历
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static String getHighEdu(Integer value){
		switch(value){
		case 1:
			return "小学及以下";
		case 2:
			return "初中";
		case 3:
			return "高中";
		case 4:
			return "中专";
		case 5:
			return "大专";
		case 6:
			return "本科";
		case 7:
			return "研究生";
		case 8:
			return "博士及以上";
		}
		return "";
	}
	/**
	 * 功能：获得学员学位
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static Integer getHighDegree(String value){
		Integer rs = -1;
		if("学士".equalsIgnoreCase(value)){
			rs = 1;
		}else if("硕士".equalsIgnoreCase(value)){
			rs = 2;
		}else if("博士".equalsIgnoreCase(value)){
			rs = 3;
		}else{
			rs  = -1;
		}
		return rs;
	}
	/**
	 * 功能：获得学员学位
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static String getHighDegree(Integer value){
        switch(value){
        case 1:
        	return "学士";
        case 2:
        	return "硕士";
        case 3:
        	return "博士";
        }
		return "";
	}
	/**
	 * 功能：获得是or否
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static Integer getYesOrNo(String value){
		Integer rs  = -1;
		if("是".equalsIgnoreCase(value)){
			rs  = 1;
		}else if("否".equalsIgnoreCase(value)){
			rs  = 0;
		}else{
			rs  = -1;
		}
		return rs;
	}
	/**
	 * 功能：获得是or否
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static String getYesOrNo(Integer value){
        switch(value){
        case 0:
        	return "否";
        case 1:
        	return "是";
        }
		return "";
	}
	/**
	 * 功能：获得性别
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static Integer getGender(String value){
		Integer rs  = -1;
		if("男".equalsIgnoreCase(value)){
			rs  = 1;
		}else if("女".equalsIgnoreCase(value)){
			rs  = 0;
		}else{
			rs  = -1;
		}
		return rs;
	}
	/**
	 * 功能：获得性别
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static String getGender(Short value){
		switch(value){
		case (short)0:
			return "女";
		case (short)1:
			return "男";
		}
		return "";
	}
	/**
	 * 功能：获得账号状态
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static String getAccountState(Integer value){
		switch(value){
		case 0:
			return "启用";
		case 1:
			return "停用";
		
		}
		return "";
	}
	/**
	 * 功能：获得系统类型
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static String getUnitType(Integer value){
		switch(value){
		case 0:
			return "系统";
		case 1:
			return "单位";
		
		}
		return "";
	}
	/**
	 * 功能：获得系统类型
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static Integer getUnitType(String value){
		if("系统".equals(value))
			return 0;
		if("单位".equals(value))
			return 1;
		return -1;
	}
	/**
	 * 功能：获得系统、单位的当前运行状态
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-15
	 * @modified:Liubaofeng
	 * @modified date:2013-3-15
	 */
	public static String getUnitState(Integer value){
		switch(value){
		case 0:
			return "试用";
		case 1:
			return "正式运营";
		case 2:
			return "停用";
		case 3:
			return "删除";
		}
		return "";
	}
	/**
	 * 功能：获得政治面貌
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-7-10
	 * @modified:Liubaofeng
	 * @modified date:2013-7-10
	 */
	public static Integer getPolitical(String value) {
		if("党员".equalsIgnoreCase(value)) 
			return 1;
		if("团员".equalsIgnoreCase(value)) 
			return 2;
		if("民族党派".equalsIgnoreCase(value)) 
			return 3;
		if("无".equalsIgnoreCase(value)) 
			return 4;
		return -1;
	}
	/**
	 * 功能：获得政治面貌
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-7-10
	 * @modified:Liubaofeng
	 * @modified date:2013-7-10
	 */
	public static String getPolitical(Integer value) {
		if(null!=value){
			switch(value){
			case 1:
				return "党员";
			case 2:
				return "团员";
			case 3:
				return "民族党派";
			case 4:
				return "无";
			}
		}
	return "";
	}
	/**
	 * 功能：获得课程类型
	 * @param value
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-5-24
	 * @modified:Liubaofeng
	 * @modified date:2013-5-24
	 */
	public static String getCourseType(Integer value){
		switch(value){
			case 1:
				return "必修";
			case 2:
				return "选修";
			case 3:
				return "参考";
			}
		return "";
	}
	/**
	 * 获得资源类型
	 * @param type
	 * @return
	 */
	public static String getResourceType(short type){
		switch(type){
		case 1:
			return "多维富媒体电子书";
		case 2:
			return "音频";
		case 3: 
			return "单视频";
		case 4:
			return "三分屏";
		case 5:
			return "知识地图";
		case 6:
			return "电子书";
		case 7:
			return "文章";
		case 8:
			return "图片";
		case 9:
			return "微视频";
		}

		return "";
	}

	/**
	 * 获得资源类型
	 * @param typeName
	 * @return
	 */
	public static Short getResourceTypeValue(String typeName){
		switch(typeName){
			case "多维富媒体电子书":
				return 1;
			case "音频":
				return 2;
			case "单视频":
				return 3;
			case "三分屏":
				return 4;
			case "知识地图":
				return 5;
			case "电子书":
				return 6;
			case "文章":
				return 7;
			case "图片":
				return 8;
			case "微视频":
				return 9;
		}
		return 0;
	}

	/**
	 * 类型
	 */
	public static Integer getColumnClassifyType (Long columnId){
		switch (columnId.intValue()){
			case 1:
				return 0;
			case 2:
				return 5;
			case 3:
				return 6;
		}
		return 0;
	}
	/**
	 * 获得选学要求
	 * @param state
	 * @return
	 */
	public static String getSelState(short state){
		switch(state){
		case 1:
			return "必修";
		case 2:
			return "选修";
		case 3: 
			return "参考";
		}
		return "";
	}
	
	
	/**
	 * 把汉字转换为拼音
	 * @param s
	 * @return
	 */
	public static String getPingYin(String s){
	    char[] t1 = null;  
	    t1=s.toCharArray();  
	    String[] t2 = new String[t1.length];  
	    HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
	    t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	    t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	    t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
	    String t4="";  
	    int t0=t1.length;  
	    try {  
	      for (int i=0;i<t0;i++){  
	    	  //判断是否为汉字字符  
	         if(Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")){
	              t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
	              t4+=t2[0];
	         }else{
	              t4+= Character.toString(t1[i]);
	         }
	      }  
	      return t4;  
	    }  
	    catch (BadHanyuPinyinOutputFormatCombination e1) {  
	      e1.printStackTrace();  
	    }  
	    return t4;  
	} 
	/**
	 * 功能：提取字符串中括号内的字符串 
	 * 示例：中共中央于(2012)年(11)月召开，中共中央第十八次代表大会。
	 * 提取结果：2012,11
	 * @param source
	 * @return str1,str2
	 * @author:Liubaofeng
	 * @create date:2012-12-12
	 * @modified:Liubaofeng
	 * @modified date:2012-12-12
	 */
	public static String findSource(String source){
		StringBuffer str = new StringBuffer();
		String result = "";
		String find = "[\\(\\（]+.*?[\\）\\)]+"; 
		Pattern pattern = Pattern.compile(find);
	    Matcher matcher = pattern.matcher(source);
	    while (matcher.find()) {
	    	String item = matcher.group();
	    	item = item.substring(1, item.length()-1);
	    	str.append(item).append(",");
	    }
	    if(str.length()>0){
	    	result = str.substring(0, str.length()-1);
	    	
	    }
	   return result;	
	}
	/**
	 * 功能：替换指定字符串
	 * 示例：中共中央于(2012)年(11)月召开，中共中央第十八次代表大会。
	 * 提取结果：中共中央于(_1_)年(_2_)月召开，中共中央第十八次代表大会。
	 * @param source
	 * @return
	 * @author:Liubaofeng
	 * @create date:2012-12-12
	 * @modified:Liubaofeng
	 * @modified date:2012-12-12
	 */
	public static String replaceStrQuery(String source){
		String find = "[\\(\\（]+.*?[\\）\\)]+"; 
		Pattern pattern = Pattern.compile(find);
	    Matcher matcher = pattern.matcher(source);
	    while (matcher.find()) {
	    	source = source.replace(matcher.group(), "（__）");
	    }
		return source;
	}
	public static String replaceStrSave(String source){
		String find = "[\\(\\（]+.*?[\\）\\)]+"; 
		Pattern pattern = Pattern.compile(find);
	    Matcher matcher = pattern.matcher(source);
	    int i = 1;
	    while (matcher.find()) {
	    	source = source.replace(matcher.group(), "（_"+i+"_）");
	    	i++;
	    }
		return source;
	}
	/**
	 * 功能：计算字符串中占位符数量
	 * @param source
	 * @return
	 * @author:Liubaofeng
	 * @create date:2012-12-28
	 * @modified:Liubaofeng
	 * @modified date:2012-12-28
	 */
	public static Integer countPlaceholder(String source){
		String find = "[\\(\\（]+.*?[\\）\\)]+"; 
		Pattern pattern = Pattern.compile(find);
	    Matcher matcher = pattern.matcher(source);
	    Integer i = 0;
	    while (matcher.find()) {
	    	i++;
	    }
		return i;
	}
	/**
	 *功能：合并字符串
	 * 示例：
	 * 字符串1：中共中央于( )年( )月召开，中共()第十八次代表大会。
	 * 字符串2：2012，11
	 * 合并结果：中共中央于(2012)年(11)月召开，中共(__)第十八次代表大会。
	 * @param source
	 * @param target
	 * @return
	 * @author:Liubaofeng
	 * @create date:2013-3-13
	 * @modified:Liubaofeng
	 * @modified date:2013-3-13
	 */
	public static String replaceStrFill(String source,String target){
		String[] tarArr = target.split(",");
		String find = "[\\(\\（]+.*?[\\）\\)]+"; 
		Pattern pattern = Pattern.compile(find);
	    Matcher matcher = pattern.matcher(source);
	    int i = 0;
	    while (matcher.find()) {
	    	if(i<tarArr.length && !"".equals(tarArr[i])){
	    		source = source.replace(matcher.group(), "("+tarArr[i]+")");
	    	}else{
	    		source = source.replace(matcher.group(), "(__)");
	    	}
	    	i++;
	    }
			return source;
		}
	/**
	 * 功能：Array转String字符串
	 * @param strArr
	 * @return
	 * @author:Liubaofeng
	 * @create date:2012-12-14
	 * @modified:Liubaofeng
	 * @modified date:2012-12-14
	 */
	public static String arrayToStr(String[] strArr){
		String sbr = "";
		if(null!=strArr && strArr.length>0){
			for(String str:strArr){
				if(StringUtil.isNotEmpty(str)){
					sbr=sbr+str+",";
				}
			}
			if(sbr.length()>0){
				sbr = sbr.substring(0, sbr.length()-1);
			}
		}
		return sbr;
	}
	/**
	 * 功能：合并字符串
	 * 示例：
	 * 字符串1：中共中央于( )年( )月召开，中共中央第十八次代表大会。
	 * 字符串2：2012，11
	 * 合并结果：中共中央于______年______月召开，中共中央第十八次代表大会。
	 * @param source
	 * @param target
	 * @return
	 * @author:Liubaofeng
	 * @create date:2012-12-13
	 * @modified:Liubaofeng
	 * @modified date:2012-12-13
	 */
	public static String mergerStr(String source,String target,Long epqId,String flag,int index){
		
		int length = lastNum(source);
		
		for(int i=0;i<length;i++){
			String find = "[\\(\\（]+(_"+(i+1)+"_)+[\\）\\)]+"; 
			Pattern pattern = Pattern.compile(find);
			Matcher matcher = pattern.matcher(source);
			if(matcher.find()) {
				String bank = "<input type='text' id='reanswer"+index+"' name='erVo.epqList["+index+"].reanswer'  style='height: 22px; border:0px; border-bottom: #417ac1 1px dashed;background: #ffffff;width:200px; line-height:22px;color:#ff0000;'/>";
				if("2".equals(flag)){
					bank = "<input type='text' id='reanswer"+index+"' name='erVo.epqList["+index+"].reanswer'  style='height: 22px; border:0px; border-bottom: #417ac1 1px dashed;background: #ffffff;width:200px; line-height:22px;color:#ff0000;' readonly='readonly' />";
				}
				source = source.replace(matcher.group(), bank);
			}
		}
		return source;
	}
	
	private static int lastNum(String source){
		String num = "[\\d]+"; 
		Pattern numPt = Pattern.compile(num);
		Matcher numMat = numPt.matcher(source);
		String rs = "";
		while(numMat.find()) {
			rs = numMat.group();
		}
		int i = StringUtil.isNotEmpty(rs)?Integer.parseInt(rs):0;
		return i;
	}
	/**
	 * 获得图片地址域名信息
	 * @param url
	 * @return
	 */
	public static String getImgUrlAddr(String url){
		if(StringUtil.isNotEmpty(url)){
			return AddrPropertiesUtil.getPropertyValue("imgUrl")+url; 
		}
		return "";
	}
	/**
	 * 获得知识地图图片地址
	 * @param id
	 * @return
	 */
	public static String getKmImageUrl(long id){
		StringBuilder imageUrl = new StringBuilder(AddrPropertiesUtil.getPropertyValue("kmUrl"));
		long i = id%15 + 1;
		imageUrl.append("/").append(Constants.KM_IMAGE_PATH).append("/").append(i).append(".jpg");
		return imageUrl.toString();
	}
	/**
	 * 得到所有资源的图片信息
	 * @param type
	 * @param imgUrl
	 * @param id
	 * @return
	 */
	public static String getImageUrl(Short type, String imgUrl, Long id){
		if("5".equals(String.valueOf(type))){
			return getKmImageUrl(id);
		}else{
			return getImgUrlAddr(imgUrl);
		}
	}
	/**
	 * 得到所有资源的图片信息
	 * @param type
	 * @param imgUrl
	 * @param id
	 * @param shareType
	 * @return
	 */
	public static String getImageUrl(Short type, String imgUrl, Long id, Short shareType){
		if("5".equals(String.valueOf(type))){
			return getKmImageUrl(id);
		}else{
			return getImgUrlAddr(imgUrl, shareType);
		}
	}
	/**
	 * 获得图片地址域名信息
	 * @param url
	 * @return
	 */
	public static String getImgUrlAddr(String url, Short shareType){
		if(StringUtil.isNotEmpty(url)){
			if(3 == shareType){
				return AddrPropertiesUtil.getPropertyValue("imgUrl")+url;
			}else{
				return AddrPropertiesUtil.getPropertyValue("appImgUrl")+url;
			}
		}
		return "";
	}
	/**
	 * 获得资源地址域名信息
	 * @param url
	 * @param shareType
	 * @return
	 */
	public static String getResourceUrlAddr(String url, Short shareType){
		if(StringUtil.isNotEmpty(url)){
			if(3 == shareType){
				return AddrPropertiesUtil.getPropertyValue("resourceUrl")+url;
			}else{
				return AddrPropertiesUtil.getPropertyValue("localResourceUrl")+url;
			}
		}
		return "";
	}
	/**
	 * 正则表达式判断
	 * @param reg 正则表达式 
	 * @param str 判断的字符串
	 * 
	 * */
	public static boolean regular(String reg,String str){
        boolean tem=false;
        if(isNotEmpty(reg)&&isNotEmpty(str)){
            Pattern pattern = Pattern.compile(reg);  
            Matcher matcher=pattern.matcher(str);
            tem=matcher.matches();  	
        }
        return tem;  
    }
	/**
	 * 将",1,23,24,34,..."转化为List<Long> 集合
	 *
	 * */
	public static List<Long> getStringToLong(String str){
		List<Long> list= Lists.newArrayList();
		if(isNotEmpty(str)){
			String[] ids=str.split(",");
			for(int i=0;i<ids.length;i++){
				if(StringUtil.isNotEmpty(ids[i])){
					list.add(Long.parseLong(ids[i]));
				}
			}
		}
		return list;
	}
	public static void main(String[] args) {
//		System.out.println(replaceStr("1+（5）+(6)=12"));
//		System.out.println(findSource("1+（5）+(6)=12"));
//		
		//String s = "住址（_1_），民族（_2_）（）（）（）（）";
		
//		System.out.println(mergerStr(s,a));
		
		//System.out.println(countPlaceholder(s));
		
		/**String find = "[\\d]+"; 
		Pattern pattern = Pattern.compile(find);
		Matcher matcher = pattern.matcher("sdfs12中毒u哦撒地方4得分但是3阿斯蒂芬");
		String rs = "";
		while(matcher.find()) {
			rs = matcher.group();
		}
		System.out.println(rs);**/
		System.out.println("1:"+getRandomString("0123456789", 10, 8));
		System.out.println("2:"+getRandomString("0123456789", 10, 8));
		System.out.println("3:"+getRandomString("0123456789", 10, 8));
		System.out.println("4:"+getRandomString("0123456789", 10, 8));
		
	}
	public static String recurrence(List<CurrencyVo> list, Long parentId) {
		{
			StringBuilder json = new StringBuilder();
			for (CurrencyVo queryVo : list) {
				if(queryVo.getParentId().equals(parentId)){
					json.append("{title:'").append(queryVo.getName());
					String son = recurrence(list, queryVo.getId());
					if(Strings.isNullOrEmpty(son)){
						json.append("',type:'item',attr: {id:").append(queryVo.getId()).append("}},");
					}else{
						json.append("',type:'folder',attr: {id:").append(queryVo.getId()).append("},products: [")
								.append(son)
								.append("]},");
					}
				}
			}
			if(null != json && json.length() > 0){
				json.deleteCharAt(json.length() - 1);
			}
			return json.toString();
		}
	}
	public static String recat(List<ExamSubjectCatVo> list, Long parentId) {
		{
			StringBuilder json = new StringBuilder();
			for (ExamSubjectCatVo queryVo : list) {
				if(queryVo.getParentId().equals(Integer.parseInt(parentId.toString()))){
					json.append("{title:'").append(queryVo.getName());
					String son = recat(list, queryVo.getId());
					if(Strings.isNullOrEmpty(son)){
						json.append("',type:'item',attr: {id:").append(queryVo.getId()).append("}},");
					}else{
						json.append("',type:'folder',attr: {id:").append(queryVo.getId()).append("},products: [")
								.append(son)
								.append("]},");
					}
				}
			}
			if(null != json && json.length() > 0){
				json.deleteCharAt(json.length() - 1);
			}
			return json.toString();
		}
	}
	public static boolean isOk(String input) {
		return input != null && input.trim().length() > 0;
	}

	/**
	 * 根据栏目ID获取resourceUrl
	 * @param articleId
	 * @return
	 */
	public static String getResourceUrl(Long articleId ,StringBuffer url, String type) {
		return url.append(articleId).append(type).append(articleId).toString();
	}

	/***
	 * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言
	 * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
	 * ^[\u4E00-\u9FA5]+$ 匹配简体
	 */
	private static String convertHanzi2Pinyin(String hanzi) {
		String regExp="^[\u4E00-\u9FFF]+$";
		StringBuffer sb=new StringBuffer();
		if(hanzi==null||"".equals(hanzi.trim())) {
			return "";
		}
		String pinyin="";
		for(int i=0;i<hanzi.length();i++) {
			char unit=hanzi.charAt(i);
			pinyin=convertSingleHanzi2Pinyin(unit);
			sb.append(pinyin.charAt(0));
		}
		return sb.toString();
	}
	private static String convertSingleHanzi2Pinyin(char hanzi) {
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String[] res;
		StringBuffer sb=new StringBuffer();
		try {
			res = PinyinHelper.toHanyuPinyinStringArray(hanzi,outputFormat);
			sb.append(res[0]);//对于多音字，只用第一个拼音
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return sb.toString();
	}
	public static String conver(String str){
		return toUpperCaseFirstOne(convertHanzi2Pinyin(str));
	}
	private static String toUpperCaseFirstOne(String s){
		return  (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(Character.toUpperCase(s.charAt(1))).toString();
	}
}
