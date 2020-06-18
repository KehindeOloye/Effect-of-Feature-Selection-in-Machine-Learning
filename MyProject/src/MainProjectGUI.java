
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;

/**
 *
 * @author Kehinde
 */
public class MainProjectGUI extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static SAXBuilder mysaxbuilder = new SAXBuilder();
    static Document document;
    static Element myrootelement;
    private JFileChooser mychooser;
    /**
     * Creates new form Parser
     */
    public MainProjectGUI() throws FileNotFoundException {
        
        initComponents();
        
        //jPanel1.setSize(new Dimension(2000,1600));
        jPanel1.setSize( 1024, 768);
        jButton1.setText("OPEN XML FILE");
        jButton2.setText("PARSE FILE");
        jLabel1.setText("");
        jButton3.setText("FEATURE_SELECT(SUPERVISED)");
        jButton4.setText("FEATURE_SELECT(UNSUPERVISED)");
        jButton5.setText("NO_FEATURE_SELECT(SUPERVISED)");
        jButton6.setText("NO_FEATURE_SELECT(UNSUPERVISED)");
        jButton7.setText("CLICK HERE FOR HELP");
        jButton7.setFocusPainted(false);
        jButton7.setMargin(new Insets(0, 0, 0, 0));
        jButton7.setContentAreaFilled(false);
        jButton7.setBorderPainted(false);
        jButton7.setOpaque(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton7.setForeground(Color.WHITE);
        /**jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        jTextArea3.setEditable(false);**/
        jButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (mychooser == null) {
                        mychooser = new JFileChooser();
                        mychooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        mychooser.setAcceptAllFileFilterUsed(false);
                        mychooser.addChoosableFileFilter(new FileFilter() {
                            @Override
                            public boolean accept(File myfile) {
                                return myfile.isDirectory() || myfile.getName().toLowerCase().endsWith(".xml");
                            }
                            
                            @Override
                            public String getDescription() {
                                return "Text Files (*.xml)";
                            }
                        });
                    }
                    switch (mychooser.showOpenDialog(MainProjectGUI.this)) {
                        case JFileChooser.APPROVE_OPTION:
                            try (BufferedReader mybufferedReader = new BufferedReader(new FileReader(mychooser.getSelectedFile()))) {
                                jTextArea1.setText(null);
                                String text = null;
                                while ((text = mybufferedReader.readLine()) != null) {
                                    jTextArea1.append(text);
                                    jTextArea1.append("\n");
                                }
                                
                                jTextArea1.setCaretPosition(0);
                                
                            } catch (IOException exp) {
                                exp.printStackTrace();
                                JOptionPane.showMessageDialog(MainProjectGUI.this, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            jButton2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                     jTextArea2.setText(null);
                                    try {
                                        File file = mychooser.getSelectedFile();
                                        SAXBuilder saxBuilder = new SAXBuilder();
                                        Document document = saxBuilder.build(file);
                                        //PrintStream out = new PrintStream(new FileOutputStream("output.csv"),true);
                                        //System.setOut(out);
                                        System.out.println("The Features of the selected file are as follows:");
                                        System.out.println("The features are divided into: Report Version, Configuration and analysis subject");
                                        Element classElement = document.getRootElement();
                                        
                                        Element reportVersion = classElement.getChild("report_version");
                                        String myreportVersion = "Number of Major Report Version: "+reportVersion.getChild("major").getText()+" "+"Number of Minor Report Version: "+reportVersion.getChild("minor").getText();
                                        System.out.println("The report version details include: number of major report version, number of minor report version, report version summary");
                                        System.out.println(myreportVersion);
                                        Element summary = reportVersion.getChild("summary");
                                        String mysummary ="The summary values are mostly boolean, representing their states"+"\r\n"+"Summary includes the following: "+"\r\n"+"Auto start: "+summary.getChild("auto_start").getText()+"\r\n"+"Internet settings: "+summary.getChild("internet_settings").getText()+"\r\n"+"Browser Help Object(BHO): "+summary.getChild("bho").getText()+"\r\n"+"Window directory"+summary.getChild("win_dir_copy").getText()+"\r\n"+"Av_kill: "+summary.getChild("av_kill").getText()+"\r\n"+"Com_Object:"+summary.getChild("com_object").getText()+"\r\n"+"Distilled Log Format(dlf): "+summary.getChild("dlf").getText()+"\r\n"+"Internet Relay Chat bot(ircbot): "+summary.getChild("ircbot").getText()+"\r\n"+"Spam bot: "+summary.getChild("spambot").getText()+"\r\n"+"Address scan: "+summary.getChild("addressscan").getText()+"\r\n"+"Port Scan: "+summary.getChild("portscan").getText()+"\r\n"+"File Modification Destruction: "+summary.getChild("file_modification_destruction").getText()+"\r\n"+"Process Spawn: "+summary.getChild("process_spawn").getText()+"\r\n"+"All Registry Activities: "+summary.getChild("all_reg_activities").getText()+"\r\n"+"Write to Foreign Memory Area: "+summary.getChild("write_to_foreign_mem_area").getText()+"\r\n"+"Install Service: "+summary.getChild("install_service").getText()+"\r\n"+"Load Driver: "+summary.getChild("load_driver").getText()+"\r\n"+"Install IE Toolbar: "+summary.getChild("install_ie_toolbar").getText()+"\r\n"+"Disable Windows Update: "+summary.getChild("disable_win_update").getText()+"\r\n"+"Change Windows Firewall Settings: "+summary.getChild("change_win_firewall_settings").getText()+"\r\n"+"Harvesting Emails: "+summary.getChild("harvesting_emails").getText()+"\r\n"+"Modify System Files: "+summary.getChild("mod_sys_files").getText()+"\r\n"+"Modify Files Only in User Directory: "+summary.getChild("modify_files_only_in_user_dir").getText()+"\r\n"+"Packed Binary: "+summary.getChild("packed_binary").getText()+"\r\n"+"Av_hit: "+summary.getChild("av_hit").getText()+"\r\n"+"Crash: "+summary.getChild("crash").getText()+"\r\n"+"Autorun: "+summary.getChild("autorun").getText()+"\r\n"+"Severity Level: "+summary.getChild("severity_level").getText();
                                        System.out.println(mysummary);
                                        System.out.println("**************************************************************************"+"\r\n");
                                        System.out.println("The configuration details include: time_needed, report_created, termination_reason and ttanalyze_version");
                                        Element configuration = classElement.getChild("configuration");
                                        String myconfiguration = "The configuration details are as follows:"+"\r\n"+"Time Needed: "+configuration.getChild("time_needed").getText()+"\r\n"+"Report Created: "+configuration.getChild("report_created").getText()+"\r\n"+"Termination Reason: "+configuration.getChild("termination_reason").getText();
                                        System.out.println(myconfiguration);
                                        Element ttanalyze_version = configuration.getChild("ttanalyze_version");
                                        System.out.println("The ttanalyze_version of configuration details has the following sub-elements:");
                                        String myttanalyze_version = "Program version: "+ttanalyze_version.getChild("prog_version").getText()+"\r\n"+"SVN Revision: "+ttanalyze_version.getChild("svn_revision").getText()+"\r\n"+"Build Date: "+ttanalyze_version.getChild("build_date").getText();
                                        System.out.println(myttanalyze_version);
                                        System.out.println("**************************************************************************"+"\r\n");
                                        
                                        System.out.println("The analysis_subject details include: general details, dll_dependencies and activities");
                                        System.out.println("The general details of analysis_subject details has the following sub-elements:");
                                        Element analysis_subject = classElement.getChild("analysis_subject");
                                        Element general = analysis_subject.getChild("general");
                                        String mygeneral = "ID: "+general.getChild("id").getText()+"\r\n"+"Parent ID: "+general.getChild("parent_id").getText()+"\r\n"+"Analysis Reason: "+general.getChild("analysis_reason").getText()+"\r\n"+"Submission Function: "+general.getChild("submission_fn").getText()+"\r\n"+"Virtual Function: "+general.getChild("virtual_fn").getText()+"\r\n"+"Virtual Path: "+general.getChild("virtual_path").getText()+"\r\n"+"Arguments: "+general.getChild("arguments").getText()+"\r\n"+"Status: "+general.getChild("status").getText()+"\r\n"+"Exit Code: "+general.getChild("exit_code").getText()+"\r\n"+"MD5 Hash: "+general.getChild("md5").getText()+"\r\n"+"SHA1 Hash: "+general.getChild("sha1").getText()+"\r\n"+"File Size: "+general.getChild("file_size").getText();
                                        System.out.println(mygeneral);
                                        System.out.println("The dll_dependencies details of analysis_subject details has the following sub-elements:");
                                        Element dll_dependencies = analysis_subject.getChild("dll_dependencies");
                                        List<Element> loaded_dllList = dll_dependencies.getChildren("loaded_dll");
                                        for (int temp = 0; temp < loaded_dllList.size(); temp++) {
                                            Element loaded_dll = loaded_dllList.get(temp);
                                            Attribute base_address =  loaded_dll.getAttribute("base_address");
                                            Attribute base_name =  loaded_dll.getAttribute("base_name");
                                            Attribute full_name =  loaded_dll.getAttribute("full_name");
                                            Attribute is_load_time_dependency =  loaded_dll.getAttribute("is_load_time_dependency");
                                            Attribute load_time =  loaded_dll.getAttribute("load_time");
                                            Attribute size =  loaded_dll.getAttribute("size");
                                            //myloaded_dll[] = new String[temp];
                                            System.out.print("\r\n"+"Base Address"+temp+": "+base_address.getValue()+"\r\n"+"Base Name"+temp+": "+base_name.getValue()+"\r\n"+"Full Name"+temp+": "+full_name.getValue()+"\r\n"+"Is Load Time Dependency"+temp+": "+is_load_time_dependency.getValue()+"\r\n"+"Load Time"+temp+": "+load_time.getValue()+"\r\n"+"Size"+temp+": "+size.getValue()+"\r\n");
                                            //System.out.print(base_address.getValue()+","+base_name.getValue()+","+full_name.getValue()+","+is_load_time_dependency.getValue()+","+load_time.getValue()+","+size.getValue()); 
            
                                        }
                                        System.out.println("The activities details of analysis_subject details has the following sub-elements: File Activities and Registry Activities");
                                        System.out.println("The Registry activities  of activities details has the following sub-elements:");
                                        
                                        Element activities = analysis_subject.getChild("activities");
                                        Element registry_activities = activities.getChild("registry_activities");
                                        List<Element> reg_value_readList = registry_activities.getChildren("reg_value_read");
                                        for (int tmp = 0; tmp < reg_value_readList.size(); tmp++) {
                                            Element reg_value_read = reg_value_readList.get(tmp);
                                            Attribute count =  reg_value_read.getAttribute("count");
                                            Attribute key =  reg_value_read.getAttribute("key");
                                            Attribute value_data =  reg_value_read.getAttribute("value_data");
                                            Attribute value_name =  reg_value_read.getAttribute("value_name");
                                            System.out.print("\r\n"+"Count"+tmp+": "+count.getValue()+"\r\n"+"Key"+tmp+": "+key.getValue()+"\r\n"+"Value Data"+tmp+": "+value_data.getValue()+"\r\n"+"Value Name"+tmp+": "+value_name.getValue()+"\r\n");
                                            //System.out.print(count.getValue()+","+key.getValue()+","+value_data.getValue()+","+value_name.getValue());
            
                                        }
                                        System.out.println("The File activities  of activities details has the following sub-elements: Fs_control_communication and Device_control_communication");
                                        System.out.println("The Fs_control_communication activities  of file activities has the following sub-elements:");
                                        
                                        Element file_activities = activities.getChild("file_activities");
                                        Element fs_control_communicationElement = file_activities.getChild("fs_control_communication");
                                        Attribute control_code =  fs_control_communicationElement.getAttribute("control_code");
                                        Attribute count1 =  fs_control_communicationElement.getAttribute("count");
                                        Attribute file1 =  fs_control_communicationElement.getAttribute("file");
                                        String myfs_control_communicationElement = "Control code: "+control_code.getValue()+"\r\n"+"Count: "+count1.getValue()+"\r\n"+"File: "+file1.getValue();
                                        System.out.println(myfs_control_communicationElement);
                                        System.out.println("The Device_control_communication activities  of file activities has the following sub-elements:");
                                        
                                        Element device_control_communicationElement = file_activities.getChild("device_control_communication");
                                        Attribute control_code1 =  device_control_communicationElement.getAttribute("control_code");
                                        Attribute count2 =  device_control_communicationElement.getAttribute("count");
                                        Attribute file2 =  device_control_communicationElement.getAttribute("file");
                                        String mydevice_control_communicationElement ="Control code: "+control_code1.getValue()+"\r\n"+"Count: "+count2.getValue()+"\r\n"+"File: "+file2.getValue();
                                        System.out.println(mydevice_control_communicationElement);                                        
                                        // System.out.println(myreportVersion+","+mysummary+","+myconfiguration+","+myttanalyze_version+","+mygeneral+","+myloaded_dll+","+myreg_value_read+""+myfs_control_communicationElement+","+mydevice_control_communicationElement);
                                        System.out.println("**************************************************************************"+"\r\n");
                                        System.out.println("THE ABOVE DETAILS IN .CSV FORMAT IS GIVEN BELOW:");
                                        String csvmyreportVersion = reportVersion.getChild("major").getText()+","+reportVersion.getChild("minor").getText();
                                        String csvmysummary =summary.getChild("auto_start").getText()+","+summary.getChild("internet_settings").getText()+","+summary.getChild("bho").getText()+","+summary.getChild("win_dir_copy").getText()+","+summary.getChild("av_kill").getText()+","+summary.getChild("com_object").getText()+","+summary.getChild("dlf").getText()+","+summary.getChild("ircbot").getText()+","+summary.getChild("spambot").getText()+","+summary.getChild("addressscan").getText()+","+summary.getChild("portscan").getText()+","+summary.getChild("file_modification_destruction").getText()+","+summary.getChild("process_spawn").getText()+","+summary.getChild("all_reg_activities").getText()+","+summary.getChild("write_to_foreign_mem_area").getText()+","+summary.getChild("install_service").getText()+","+summary.getChild("load_driver").getText()+","+summary.getChild("install_ie_toolbar").getText()+","+summary.getChild("disable_win_update").getText()+","+summary.getChild("change_win_firewall_settings").getText()+","+summary.getChild("harvesting_emails").getText()+","+summary.getChild("mod_sys_files").getText()+","+summary.getChild("modify_files_only_in_user_dir").getText()+","+summary.getChild("packed_binary").getText()+","+summary.getChild("av_hit").getText()+","+summary.getChild("crash").getText()+","+summary.getChild("autorun").getText()+","+summary.getChild("severity_level").getText(); 
                                        String csvmyconfiguration = configuration.getChild("time_needed").getText()+","+configuration.getChild("report_created").getText()+","+configuration.getChild("termination_reason").getText();
                                        String csvmyttanalyze_version = ttanalyze_version.getChild("prog_version").getText()+","+ttanalyze_version.getChild("svn_revision").getText()+","+ttanalyze_version.getChild("build_date").getText();
                                        String csvmygeneral = general.getChild("id").getText()+","+general.getChild("parent_id").getText()+","+general.getChild("analysis_reason").getText()+","+general.getChild("submission_fn").getText()+","+general.getChild("virtual_fn").getText()+","+general.getChild("virtual_path").getText()+","+general.getChild("arguments").getText()+","+general.getChild("status").getText()+","+general.getChild("exit_code").getText()+","+general.getChild("md5").getText()+","+general.getChild("sha1").getText()+","+general.getChild("file_size").getText();
                                        String csvmyfs_control_communicationElement = control_code.getValue()+","+count1.getValue()+","+file1.getValue();  
                                        String csvmydevice_control_communicationElement =control_code1.getValue()+","+count2.getValue()+","+file2.getValue();  
       
                                        System.out.println(csvmyreportVersion+","+csvmysummary+","+csvmyconfiguration+","+csvmyttanalyze_version+","+csvmygeneral+","+""+csvmyfs_control_communicationElement+","+csvmydevice_control_communicationElement);
                                        //TextAreaOutputStream textAreaOutputStream = new TextAreaOutputStream(jTextArea2);
                                        System.setOut(new PrintStream(new MyTextareaoutputStream(jTextArea2)));
                                    } catch (JDOMException ex) {
                                        Logger.getLogger(MainProjectGUI.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(MainProjectGUI.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            break;
                    }
                }
            });
        jButton7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                 JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                 JOptionPane.showMessageDialog(frame,
        "The interface consists of an open button which when pressed provides a means"+"\r\n"+"of choosing an xml file which will be viewed in a text area. The second button is the parse file " +"\r\n"+" button, which upon being pressed the features of the selected file is displayed in the text area,"+"\r\n"+" while the generation of the CSV file is done in the background. We use WEKA API to access the "+"\r\n"+ " required classes for feature selection and machine learning using buttons FEATURE_SELECT(SUPERVISED), "+"\r\n"+ " FEATURE_SELECT(UNSUPERVISED), NO_FEATURE_SELECT(SUPERVISED) and NO_FEATURE_SELECT(UNSUPERVISED)");
  
                     
                
                }
        });
        jButton6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                jTextArea3.setText(null);
                UnsupervisedClassesToClusterswithoutfeatureselect myUnsupervisedClassesToClusterswithoutfeatureselect = new UnsupervisedClassesToClusterswithoutfeatureselect();  
                try {
					myUnsupervisedClassesToClusterswithoutfeatureselect.myUnsupervisednofeatureselect();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.setOut(new PrintStream(new MyTextareaoutputStream(jTextArea3)));
                }
        });
        jButton4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                jTextArea3.setText(null);
                Unsupervisedwithfeatureselection myUnsupervisedwithfeatureselection = new Unsupervisedwithfeatureselection();  
                  
                        try {
							myUnsupervisedwithfeatureselection.myUnsupervisedfeatureselect();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    
                System.setOut(new PrintStream(new MyTextareaoutputStream(jTextArea3)));
                }
        });
        jButton3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                jTextArea3.setText(null);
                Supervisedwithfeatureselection mySupervisedwithfeatureselection = new Supervisedwithfeatureselection();  
                    try {
                        mySupervisedwithfeatureselection.mySupervisedwithfeatureselection();
                    } catch (Exception ex) {
                        Logger.getLogger(MainProjectGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                System.setOut(new PrintStream(new MyTextareaoutputStream(jTextArea3)));
                }
        });
        jButton5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                jTextArea3.setText(null);
                SupervisedwithoutFeatureselect mySupervisedwithoutFeatureselect = new SupervisedwithoutFeatureselect();  
                    try {
                        mySupervisedwithoutFeatureselect.mySupervisedwithoutFeatureselect();
                    } catch (Exception ex) {
                        Logger.getLogger(MainProjectGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                System.setOut(new PrintStream(new MyTextareaoutputStream(jTextArea3)));
                }
        });
       // UnsupervisedClassesToClusterswithoutfeatureselect
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("jButton1");

        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/Kehinde/NetBeansProjects/ParseXmlFile/Parser.png")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(699, 240));
        jLabel1.setMinimumSize(new java.awt.Dimension(699, 240));
        jLabel1.setPreferredSize(new java.awt.Dimension(699, 240));

        jButton2.setText("jButton2");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("WELCOME TO OUR XML PARSER/MACHINE LEARNING DOMAIN");
        jLabel2.setBorder(new javax.swing.border.MatteBorder(null));

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        jButton5.setText("jButton5");

        jButton6.setText("jButton6");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jButton7.setText("jButton7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(106, 106, 106)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(274, 274, 274))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(537, 537, 537)
                        .addComponent(jButton7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(553, 553, 553))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainProjectGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainProjectGUI().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainProjectGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration                   
}

