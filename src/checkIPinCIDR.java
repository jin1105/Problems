
public class checkIPinCIDR {
	public static void main(String[] args) {  
		//System.out.println(checkIPinCIDR("10.153.48.127", "10.153.48.0/26"));
        System.out.println(checkIPinCIDR("10.168.1.2", "10.168.0.224/23"));
        //System.out.println(checkIPinCIDR("192.168.0.1", "192.168.0.0/24"));
        //System.out.println(checkIPinCIDR("10.168.0.0", "10.168.0.0/32")); 
    }  
	
	
	
	public static boolean checkIPinCIDR(String ip,String cidr) {
		String[] ips = ip.split("\\.");  
		//根据ip地址字符串，得到ip地址的十进制，方便之后计算
        int ipAddr = (Integer.parseInt(ips[0]) << 24)  
                | (Integer.parseInt(ips[1]) << 16)  
                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);  
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));  
        //mask得到子网掩码
        int mask = 0xFFFFFFFF << (32 - type);  
        String cidrIp = cidr.replaceAll("/.*", "");  
        String[] cidrIps = cidrIp.split("\\.");  
        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)  
                | (Integer.parseInt(cidrIps[1]) << 16)  
                | (Integer.parseInt(cidrIps[2]) << 8)  
                | Integer.parseInt(cidrIps[3]);  
        /*cidrIpAddr一定属于子网内的地址，ipAddr地址和cidrIpAddr地址分别与子网掩码做与运算，得到网络号
         * 如果网络号相同，就在同一子网,返回true，否则返回false
         */
        return (ipAddr & mask) == (cidrIpAddr & mask); 
	}
}
