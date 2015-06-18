package oracle.httptst.common;

/**
 * Class containing constants which are used by in application.
 * 
 * @author      <a href="mailto:kiran.mo.kumar@oracle.com">Kiran Kumar</a>
 * @version     %I%, %G%
 * @since       1.0
 */
public class HTTP
{
    private HTTP()
    {
    }

    public class METHOD
    {
        public static final String GET     = "GET";
        public static final String POST    = "POST";
        public static final String PUT     = "PUT";
        public static final String HEAD    = "HEAD";
        public static final String OPTIONS = "OPTIONS";
        public static final String DELETE  = "DELETE";
        public static final String TRACE   = "TRACE";
    };

    public class HEADER
    {
        public static final String CONTENT_TYPE    = "Content-Type";
        public static final String CONTENT_TYPE2   = "Content-type";
        public static final String CONTENT_LENGTH  = "Content-Length";
        public static final String CONTENT_LENGTH2 = "Content-length";

        public class CONTENTTYPE
        {
            public static final String TEXT_HTML       = "text/html";
            public static final String TEXT_PLAIN      = "text/plain";
            public static final String TEXT_XML        = "text/xml";

            public static final String FORM_URLENCODED = "application/x-www-form-urlencoded";
            public static final String MULTIPART      = "multipart/form-data; boundary=";
        };

    }
}