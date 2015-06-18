package bean;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
import java.util.Properties;
import java.util.ResourceBundle;
import oracle.javatools.resourcebundle.BundleFactory;


public class UploadServlet extends HttpServlet {
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 1024 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;
    public void init() {
        // Get the file location where it would be stored.
        ResourceBundle rs = BundleFactory.getBundle("bean.resource");
        filePath = rs.getString("logfile_path");
    }
    public void doPost(HttpServletRequest request,
   HttpServletResponse response) throws ServletException,
    java.io.IOException {
        // Check that we have a file upload request
        System.out.println("inside do post");
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        if (!isMultipart) {
            System.out.println("inside do post if codn");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);
        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);
            // Process the uploaded file items
            Iterator i = fileItems.iterator();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            while (i.hasNext()) {
                FileItem fi = (FileItem)i.next();
                System.out.println("inside file path while !!");
                if (!fi.isFormField()) {
                    String fileName = fi.getName();
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file =
new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file =
new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    out.println("Uploaded Filename: " + fileName + "<br>");
                 }
            }
            out.println("</body>");
            out.println("</html>");
            //redirect to guestPage of the portal
            response.sendRedirect("browsetest.jspx");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
java.io.IOException {
        System.out.println("inside do get");
    throw new ServletException("GET method used with " + getClass().getName() +": POST method required.");
    }
}
