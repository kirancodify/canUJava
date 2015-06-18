package oracle;

/**
 * This code is used to build a web service from the Audit database over 
 * TLS client connection. WebService annotations are used to create the method.
 * 
 * @author      <a href="mailto:kiran.mo.kumar@oracle.com">Kiran Kumar</a>
 * 
 * Code was wrriten for the HTDB
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import java.nio.charset.Charset;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;


@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@WebService(serviceName="auditLogService")
public class AuditClient {

    public static class TlsClient {
        private String hostname;
        private int port;
        private boolean reuseAddress = true;

        private SSLContext sslContext;

        public TlsClient() {
        }

        public TlsClient(final String hostname, final int port) {
            this.hostname = hostname;
            this.port = port;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(final String hostname) {
            this.hostname = hostname;
        }

        public int getPort() {
            return port;
        }

        public void setPort(final int port) {
            this.port = port;
        }

        public boolean getReuseAddress() {
            return reuseAddress;
        }

        public void setReuseAddress(final boolean reuseAddress) {
            this.reuseAddress = reuseAddress;
        }

        public SSLContext getSslContext() {
            return sslContext;
        }

        public void setSslContext(final SSLContext sslContext) {
            this.sslContext = sslContext;
        }

        public void send(final Message message) throws Exception {
            final byte[] buffer = message.toByteArray();

            System.out.println("sending message to hostname: " + hostname + " port: " + port + " bytes: "
                    + new String(buffer));

            Socket socket = null;
            try {
                socket = createClientSocket();

                final OutputStream out = socket.getOutputStream();

                final String length = String.valueOf(buffer.length) + " ";

                out.write(length.getBytes());
                out.write(buffer);
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }

        public Socket createClientSocket() throws Exception {
            final SSLSocketFactory factory = sslContext.getSocketFactory();

            final Socket socket = factory.createSocket(hostname, port);

            socket.setReuseAddress(reuseAddress);

            return socket;
        }
    }

    public static class UdpClient {
        private String hostname;
        private int port;
        private boolean reuseAddress = true;

        public UdpClient() {
        }

        public UdpClient(final String hostname, final int port) {
            this.hostname = hostname;
            this.port = port;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(final String hostname) {
            this.hostname = hostname;
        }

        public int getPort() {
            return port;
        }

        public void setPort(final int port) {
            this.port = port;
        }

        public boolean getReuseAddress() {
            return reuseAddress;
        }

        public void setReuseAddress(final boolean reuseAddress) {
            this.reuseAddress = reuseAddress;
        }

        public void send(final Message message) throws Exception {
            final byte[] buffer = message.toByteArray();

            System.out.println("sending message to hostname: " + hostname + " port: " + port + " bytes: "
                    + new String(buffer));

            DatagramSocket socket = null;
            try {
                socket = createClientSocket();

                final SocketAddress address = new InetSocketAddress(getHostname(), getPort());
                final DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address);

                socket.send(packet);
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }

        public DatagramSocket createClientSocket() throws Exception {
            final DatagramSocket socket = new DatagramSocket();

            socket.setReuseAddress(reuseAddress);

            return socket;
        }
    }

    public static class Message {
        public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

        public static final byte[] UTF8_BOM = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };

        public static final String NILVALUE = "-";

        private Integer priority = null;
        private Integer version = null;
        private Date timestamp = null;
        private String hostname = null;
        private String applicationName = null;
        private String processId = null;
        private String messageId = null;
        private String structuredData = null;
        private byte[] bom = null;
        private byte[] message = null;

        public Message() {
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(final Integer priority) {
            this.priority = priority;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(final Integer version) {
            this.version = version;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(final Date timestamp) {
            this.timestamp = timestamp;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(final String hostname) {
            this.hostname = hostname;
        }

        public String getApplicationName() {
            return applicationName;
        }

        public void setApplicationName(final String applicationName) {
            this.applicationName = applicationName;
        }

        public String getProcessId() {
            return processId;
        }

        public void setProcessId(final String processId) {
            this.processId = processId;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(final String messageId) {
            this.messageId = messageId;
        }

        public String getStructuredData() {
            return structuredData;
        }

        public void setStructuredData(final String structuredData) {
            this.structuredData = structuredData;
        }

        public byte[] getBom() {
            return bom;
        }

        public void setBom(final byte[] bom) {
            this.bom = bom;
        }

        public byte[] getMessage() {
            return message;
        }

        public void setMessage(final byte[] message) {
            this.message = message;
        }

        public String getHeader() {
            final StringBuilder builder = new StringBuilder();

            builder.append("<");
            builder.append(getPriority());
            builder.append(">");
            builder.append(getVersion());
            builder.append(" ");
            if (!isEmpty(getTimestamp())) {
                builder.append(printDate(getTimestamp()));
            } else {
                builder.append(NILVALUE);
            }
            builder.append(" ");
            if (!isEmpty(getHostname())) {
                builder.append(getHostname());
            } else {
                builder.append(NILVALUE);
            }
            builder.append(" ");
            if (!isEmpty(getApplicationName())) {
                builder.append(getApplicationName());
            } else {
                builder.append(NILVALUE);
            }
            builder.append(" ");
            if (!isEmpty(getProcessId())) {
                builder.append(getProcessId());
            } else {
                builder.append(NILVALUE);
            }
            builder.append(" ");
            if (!isEmpty(getMessageId())) {
                builder.append(getMessageId());
            } else {
                builder.append(NILVALUE);
            }
            builder.append(" ");
            if (!isEmpty(getStructuredData())) {
                builder.append(getStructuredData());
            } else {
                builder.append(NILVALUE);
            }

            final String ret = builder.toString();

            return ret;
        }

        public static boolean isEmpty(final String object) {
            return (object == null) || object.isEmpty();
        }

        public static boolean isEmpty(final Object object) {
            return (object == null);
        }

        public String printDate(final Date date) {
            final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTime(date);

            final StringBuilder builder = new StringBuilder();
            builder.append(calendar.get(Calendar.YEAR));
            builder.append("-");
            final int month = calendar.get(Calendar.MONTH);
            if (month < 9) {
                builder.append("0");
            }
            builder.append(month + 1);
            builder.append("-");
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            if (day < 10) {
                builder.append("0");
            }
            builder.append(day);
            builder.append("T");
            final int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour < 10) {
                builder.append("0");
            }
            builder.append(hour);
            builder.append(":");
            final int minute = calendar.get(Calendar.MINUTE);
            if (minute < 10) {
                builder.append("0");
            }
            builder.append(minute);
            builder.append(":");
            final int second = calendar.get(Calendar.SECOND);
            if (second < 10) {
                builder.append("0");
            }
            builder.append(second);
            builder.append(".");
            final int millisecond = calendar.get(Calendar.MILLISECOND);
            if (millisecond < 10) {
                builder.append("0");
            }
            if (millisecond < 100) {
                builder.append("0");
            }
            builder.append(millisecond);
            builder.append("Z");

            final String text = builder.toString();

            return text;
        }

        public void write(final OutputStream out) throws Exception {
            final OutputStreamWriter headerOut = new OutputStreamWriter(out, UTF8_CHARSET);

            final String header = getHeader();
            headerOut.write(header);
            headerOut.flush();

            if (message != null) {
                headerOut.write(" ");
                headerOut.flush();

                if (bom != null) {
                    out.write(bom);
                }

                out.write(message);
                out.flush();
            }
        }
       
        public byte[] toByteArray() throws Exception {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();

            write(out);

            final byte[] buffer = out.toByteArray();

            return buffer;
        }
    }

    @WebMethod(exclude = true)
    public static byte[] toByteArray(final File file) throws IOException {
        final FileInputStream in = new FileInputStream(file);

        return toByteArray(in);
    }
    @WebMethod(exclude=true)
    public static byte[] toByteArray(final InputStream in) throws IOException {
        byte[] ret;
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }

            ret = out.toByteArray();
        } finally {
            in.close();
        }
        return ret;
    }

 
    @WebMethod
    public String getAuditMessage(@WebParam(name="applName") String applName, @WebParam(name="processNum") String processNum, @WebParam(name="messageNum") String messageNum, @WebParam(name="adtmsg") String adtmsg) {
        UdpClient client = new UdpClient("aisdell652", 8514);

            Message message = new Message();

            message.setPriority(Integer.valueOf(85));
            message.setVersion(Integer.valueOf(1));
            message.setTimestamp(new Date());
            message.setHostname("localhost");
            message.setApplicationName(applName);
            message.setProcessId(processNum);
            message.setMessageId(messageNum);
            message.setStructuredData("-");
            message.setBom(Message.UTF8_BOM);
            message.setMessage(adtmsg.getBytes(Message.UTF8_CHARSET));

        try {
            client.send(message);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return "Message Successfully logged";
    }
}
