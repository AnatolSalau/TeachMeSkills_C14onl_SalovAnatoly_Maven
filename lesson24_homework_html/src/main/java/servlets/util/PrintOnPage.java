package servlets.util;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

public class PrintOnPage<T,E> {
    public void printMapOnPageByPrintWriter(PrintWriter printWriter, Map<T,E[]> map) {
        map.forEach((key,value) -> {
            printWriter.print("key : "+ key + " values :");
            Arrays.stream(value).forEach(val -> printWriter.print(" " +  val));
            printWriter.print("\n");
        } );
    }
}
