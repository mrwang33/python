	//常用代码块汇总

	/**
	 * 将字符串序列转换成sql格式 a,b-->'a','b'
	 * 
	 * @param str
	 * @return
	 */
	public static String createSqlInStr(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		String[] arr = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (String s : arr) {
			sb.append("'").append(s).append("'").append(",");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}
	
	/**
	 * 将字符串list解析成 xxx,xxxx,xxx的形式
	 * @param strList
	 * @return
	 */
	public static String stringListHandler(List<String> strList) {
		String resultStr = "";
		if (strList!=null&&strList.size()>0) {
			resultStr = "";
			for (int i = 0; i < strList.size(); i++) {
				resultStr = resultStr + strList.get(i);
				if (i!=strList.size()-1) {
					resultStr = resultStr + "";
				}
			}
		}
		return resultStr;
	}
	
	/**
	 * 将2017-09-06类型字符串拆分成 2017 9 6 格式的int数组 
	 * @param dateString
	 * @return
	 */
	public static int[] splitStringToDate(String dateString) {
		int[] date = new int[3];
		String[] split = dateString.split("-");
		for (int i = 0; i < date.length; i++) {
			date[i] = Integer.parseInt(split[i]);
		}
		return date;
	}
	/** 
     * 获得指定日期的后一天 
     * @param specifiedDay 
     * @return 
     */  
    public static String getSpecifiedDayAfter(String specifiedDay) {  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day + 1);  
  
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());  
        return dayAfter;  
    } 
    
    /**
     * 获取某月的上一月 index为0是本月;为-1是上一月;-2是上上一月; 以此类推
     * @param index
     * @return
     */
    public static String[] getLastMonth(int index) {
    	Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, index);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
		String date = format.format(c.getTime());
		String[] split = date.split("-");
		return split;
    }
    
    
    public static String setJson(String[] keys,String[] values) {
    	StringBuffer sb = new StringBuffer("{");
    	for (int i = 0; i < keys.length; i++) {
    		sb.append("\""+keys[i]+"\":");
    		sb.append("\""+values[i]+"\",");
		}
    	String result = sb.substring(0, sb.length()-1);
    	result += "}";
    	return result;
    }
    
    
    /**
     * 获取当前时间
     * @return
     */
    public static String currentDate() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	return sdf.format(date);
    }
    
    /**日期字符串转long类型的时间
     * @param dateString
     * @return
     */
    public static Date DateStringToDateTime(String dateString) {
    	Date result = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if (dateString.length()<19) {
    		sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
    	try {
    		result = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * 获取指定日期是星期几
     * @param year
     * @param month
     * @param day
     * @return 星期一是0 以此类推
     */
    public static int getWeek(int year,int month,int day) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(year, month-1, day);
    	int number = calendar.get(Calendar.DAY_OF_WEEK)-2 == -1 ? 6:calendar.get(Calendar.DAY_OF_WEEK)-2;
    	return number;
    }
	
	/**
	 * 以map的value排序 只保留前十个数据
	 * @param map
	 * @return 
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		if (map.size()==1) {
			return map;
		}
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
            //只保留前十个数据
            if (result.size()>9) {
				break;
			}
        }
        return result;
    }