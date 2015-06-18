package oracle.httptst.constants;
/* 
*Java class to define Class Variables for Proxy
*
*/

public class PROXY
{
    private PROXY()
    {
    }

    public static final String LOCALHOST    = "localhost";
    public static final String START        = "start";
    public static final String STOP         = "stop";

    public static final char STARTCODE      = 's';
    public static final char STOPCODE       = 't';
    public static final char STOPSERVERCODE = 'q';

    public static final String REQ          = ".req";
    public static final String RES          = ".res";
    public static final String XML          = ".xml";

    public static final long ADMIN_TIMEOUT  = 10000;

    // formatting strings
    public static final String PRE_REQ  = "\n\t";

    public class TAG
    {
        public static final String ADMINPORT    = "adminPort";
        public static final String PROXYHOST    = "proxyHost";
        public static final String PROXYPORT    = "proxyPort";
        public static final String LISTEN       = "listen";
        public static final String URL_FILTER   = "urlFilter";
        public static final String HEADER_FILTER= "headerFilter";
        public static final String PRINT_HEADERS= "printHeaders";

        public static final String INENCODING   = "inputEncoding";
        public static final String OUTENCODING  = "outputEncoding";

        public static final String PROTOCOL  = "protocol";
        public static final String HOST      = "host";
        public static final String PORT      = "port";
        public static final String METHOD    = "method";
        public static final String URL       = "URL";
        public static final String TIMEOUT   = "timeOut";
    };

    public static String startTag(String tagName)
    {
        return "<"+tagName+">";
    }
    public static String endTag(String tagName)
    {
        return "</"+tagName+">";
    }
}
