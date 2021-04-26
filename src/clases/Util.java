/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JAVIER
 */
public class Util {

    public static boolean exportToEXEL(JTable table_uno, JTable table_dos, File file, String nombreTab_uno,
            String nombreTab_dos, String encabezado_uno, String encabezado_dos, String footer_uno,
            String footer_dos) throws WriteException {
        try {
//            System.out.println("Cantidad de filas: "+table.getRowCount());
//            System.out.println("Cantidad de columnas: "+table.getColumnCount());
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);

            WritableFont fontHeader = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.SINGLE, Colour.RED);
            WritableCellFormat formatHeader = new WritableCellFormat(fontHeader);
//            cellFormat.setWrap(true);

            WritableFont fontTabla = new WritableFont(WritableFont.ARIAL, 11);
            WritableCellFormat formatTabla = new WritableCellFormat(fontTabla);
            formatTabla.setBorder(Border.ALL, BorderLineStyle.THIN);
            
            WritableFont fontFooter = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            WritableCellFormat formatFooter = new WritableCellFormat(fontFooter);

            if (table_uno != null) {
                WritableSheet s_uno = w.createSheet(nombreTab_uno, 0);
                s_uno.addCell(new jxl.write.Label(0, 0, encabezado_uno, formatHeader));
                s_uno.addCell(new jxl.write.Label(0, 1, ""));
                for (int i = 0; i < table_uno.getColumnCount(); i++) {
                    String colName = table_uno.getColumnName(i).toUpperCase();
                    s_uno.addCell(new jxl.write.Label(i, 2, colName, formatTabla));
                    for (int j = 0; j < table_uno.getRowCount(); j++) {
                        Object objeto = table_uno.getValueAt(j, i);
//                        System.out.println("Objeto fila " + j + ", columna " + i + ": " + objeto.toString().length());
                        if (objeto != null) {
                            if (objeto instanceof java.math.BigDecimal || objeto instanceof java.lang.Integer) {                            
                                s_uno.addCell(new jxl.write.Number(i, j + 3, Double.parseDouble(objeto.toString()), formatTabla));
                            } else {
                                s_uno.addCell(new jxl.write.Label(i, j + 3, objeto.toString(), formatTabla));
                            }
                        }
                    }
                }
                s_uno.addCell(new jxl.write.Label(0, table_uno.getRowCount() + 4, footer_uno, formatFooter));
            }

            //addcell(columna ,fila,kjsk)
            if (table_dos != null) {
                WritableSheet s_dos = w.createSheet(nombreTab_dos, 0);
                s_dos.addCell(new jxl.write.Label(0, 0, encabezado_dos, formatHeader));
                s_dos.addCell(new jxl.write.Label(0, 1, ""));
                for (int i = 0; i < table_dos.getColumnCount(); i++) {
                    String colName = table_dos.getColumnName(i).toUpperCase();
                    s_dos.addCell(new jxl.write.Label(i, 2, colName, formatTabla));
                    for (int j = 0; j < table_dos.getRowCount(); j++) {
                        Object objeto = table_dos.getValueAt(j, i);
                        if (objeto != null) {
                            if (objeto instanceof java.math.BigDecimal || objeto instanceof java.lang.Integer) {
                                s_dos.addCell(new jxl.write.Number(i, j + 3, Double.parseDouble(objeto.toString()), formatTabla));
                            } else {
//                        System.out.println("entere i="+i+"j igual "+(j+1));
                                s_dos.addCell(new jxl.write.Label(i, j + 3, objeto.toString(), formatTabla));
                            }
                        }
                    }
                }
                s_dos.addCell(new jxl.write.Label(0, table_dos.getRowCount() + 4, footer_dos, formatFooter));
            }

            w.write();
            w.close();
            out.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (WriteException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean EsInt(String a) {
        try {
            int b = Integer.parseInt(a);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    //comprobando si es número
    public static boolean EsDouble(String str) {
        try {
            double db = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
     
    public static boolean IsEmpty(String a){
        if(a.equals("") || a.equals(null)){
            return true;
        }else{
            return false;
        }
    }
    
    public static void VaciarTabla(DefaultTableModel model) {
        if(model.getRowCount() > 0){
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }
    
    public static void setAnchoColumna(TableColumn columna, int ancho){
        columna.setPreferredWidth(ancho);
//        columna.setMaxWidth(ancho);
//        columna.setMinWidth(ancho);
    }
    
    public static String getFecha_actual(){
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formato);
    }
    
//    public static void crearWord(){
//        XWPFDocument document = new XWPFDocument();
//        
//        try {
//            FileOutputStream output = new FileOutputStream("Documento de prueba.docx");
//            
//            XWPFParagraph parrafo = document.createParagraph();
//            XWPFRun run = parrafo.createRun();
//            run.setText("Esto es solo probando");
//            
//            document.write(output);
//            output.close();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage().toString());
//        }        
//    }        
}