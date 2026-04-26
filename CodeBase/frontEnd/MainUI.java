// import javax.swing.*;
// import java.awt.*;
// import java.io.File;
// import javax.imageio.ImageIO;
// import java.awt.image.BufferedImage;
// import java.awt.datatransfer.DataFlavor;
// import java.util.List;

// public class MainUI {

//     static JFrame frame;

//     public static void main(String[] args) {

//         frame = new JFrame("Dot Matrix Printer");
//         frame.setSize(700, 500);

//         // 🔥 EXIT CONFIRMATION
//         frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

//         frame.addWindowListener(new java.awt.event.WindowAdapter() {
//             public void windowClosing(java.awt.event.WindowEvent e) {
//                 int choice = JOptionPane.showConfirmDialog(
//                         frame,
//                         "Are you sure you want to exit?",
//                         "Exit",
//                         JOptionPane.YES_NO_OPTION
//                 );

//                 if (choice == JOptionPane.YES_OPTION) {
//                     System.exit(0);
//                 }
//             }
//         });

//         showHomePage();

//         frame.setVisible(true);
//     }

//     // 🟢 HOME PAGE
//     static void showHomePage() {
//         JPanel panel = new JPanel(new BorderLayout());

//         JLabel title = new JLabel("DOT MATRIX IMAGE PRINTER", JLabel.CENTER);
//         title.setFont(new Font("Arial", Font.BOLD, 26));

//         JButton startBtn = new JButton("Start");
//         startBtn.setFont(new Font("Arial", Font.BOLD, 18));

//         startBtn.addActionListener(e -> showUploadPage());

//         panel.add(title, BorderLayout.CENTER);
//         panel.add(startBtn, BorderLayout.SOUTH);

//         frame.setContentPane(panel);
//         frame.revalidate();
//     }

//     // 🟡 UPLOAD PAGE (DRAG & DROP)
//     static void showUploadPage() {

//         JPanel panel = new JPanel(new BorderLayout());

//         JLabel dropArea = new JLabel("Drag & Drop Image Here", JLabel.CENTER);
//         dropArea.setFont(new Font("Arial", Font.BOLD, 18));
//         dropArea.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
//         dropArea.setPreferredSize(new Dimension(400, 200));

//         JButton uploadBtn = new JButton("Upload Image");
//         uploadBtn.setFont(new Font("Arial", Font.BOLD, 16));

//         uploadBtn.addActionListener(e -> {
//             BufferedImage img = getImageFromUser();
//             if (img != null) {
//                 showProcessPage(img);
//             }
//         });

//         // 🔥 DRAG & DROP
//         dropArea.setTransferHandler(new TransferHandler() {
//             public boolean canImport(TransferSupport support) {
//                 return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
//             }

//             public boolean importData(TransferSupport support) {
//                 try {
//                     List<File> files = (List<File>) support.getTransferable()
//                             .getTransferData(DataFlavor.javaFileListFlavor);

//                     BufferedImage img = ImageIO.read(files.get(0));
//                     showProcessPage(img);
//                     return true;

//                 } catch (Exception e) {
//                     JOptionPane.showMessageDialog(frame, "Invalid file!");
//                 }
//                 return false;
//             }
//         });

//         JPanel centerPanel = new JPanel(new GridBagLayout());
//         centerPanel.add(dropArea);

//         panel.add(centerPanel, BorderLayout.CENTER);
//         panel.add(uploadBtn, BorderLayout.SOUTH);

//         frame.setContentPane(panel);
//         frame.revalidate();
//     }

//     // 🔥 FILE PICKER
//     static BufferedImage getImageFromUser() {
//         JFileChooser chooser = new JFileChooser();
//         int result = chooser.showOpenDialog(frame);

//         if (result == JFileChooser.APPROVE_OPTION) {
//             try {
//                 File file = chooser.getSelectedFile();
//                 return ImageIO.read(file);
//             } catch (Exception e) {
//                 JOptionPane.showMessageDialog(frame, "Error loading image!");
//             }
//         }
//         return null;
//     }

//     // 🔵 PROCESS PAGE
//     static void showProcessPage(BufferedImage image) {

//         JPanel panel = new JPanel(new BorderLayout());

//         JLabel imageLabel = new JLabel();
//         imageLabel.setHorizontalAlignment(JLabel.CENTER);

//         ImageIcon icon = new ImageIcon(
//                 image.getScaledInstance(350, 300, Image.SCALE_SMOOTH)
//         );
//         imageLabel.setIcon(icon);

//         JPanel btnPanel = new JPanel();

//         JButton convertBtn = new JButton("Convert");
//         JButton printBtn = new JButton("Print");
//         JButton exitBtn = new JButton("Exit");

//         convertBtn.setFont(new Font("Arial", Font.BOLD, 16));
//         printBtn.setFont(new Font("Arial", Font.BOLD, 16));
//         exitBtn.setFont(new Font("Arial", Font.BOLD, 14));

//         btnPanel.add(convertBtn);
//         btnPanel.add(printBtn);
//         btnPanel.add(exitBtn);

//         JLabel status = new JLabel("Ready", JLabel.CENTER);

//         convertBtn.addActionListener(e -> {
//             status.setText("Converting...");
//             status.setText("Conversion Done");
//         });

//         printBtn.addActionListener(e -> {
//             status.setText("Printing...");
//             status.setText("Printed Successfully");
//         });

//         exitBtn.addActionListener(e -> {
//             int choice = JOptionPane.showConfirmDialog(
//                     frame,
//                     "Are you sure you want to exit?",
//                     "Exit",
//                     JOptionPane.YES_NO_OPTION
//             );

//             if (choice == JOptionPane.YES_OPTION) {
//                 System.exit(0);
//             }
//         });

//         panel.add(imageLabel, BorderLayout.CENTER);
//         panel.add(btnPanel, BorderLayout.NORTH);
//         panel.add(status, BorderLayout.SOUTH);

//         frame.setContentPane(panel);
//         frame.revalidate();
//     }
// }

package frontEnd;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.datatransfer.DataFlavor;
import java.util.List;

public class MainUI {

    static JFrame frame;

    public static void main(String[] args) {

        frame = new JFrame("Dot Matrix Printer");
        frame.setSize(700, 500);

        // 🔥 EXIT CONFIRMATION
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to exit?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        showHomePage();

        frame.setVisible(true);
    }

    // 🟢 HOME PAGE
    static void showHomePage() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("DOT MATRIX IMAGE PRINTER", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));

        JButton startBtn = new JButton("Start");
        startBtn.setFont(new Font("Arial", Font.BOLD, 18));

        startBtn.addActionListener(e -> showUploadPage());

        panel.add(title, BorderLayout.CENTER);
        panel.add(startBtn, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
    }

    // 🟡 UPLOAD PAGE (DRAG & DROP)
    static void showUploadPage() {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel dropArea = new JLabel("Drag & Drop Image Here", JLabel.CENTER);
        dropArea.setFont(new Font("Arial", Font.BOLD, 18));
        dropArea.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        dropArea.setPreferredSize(new Dimension(400, 200));

        JButton uploadBtn = new JButton("Upload Image");
        uploadBtn.setFont(new Font("Arial", Font.BOLD, 16));

        uploadBtn.addActionListener(e -> {
            BufferedImage img = getImageFromUser();
            if (img != null) {
                showProcessPage(img);
            }
        });

        // 🔥 DRAG & DROP
        dropArea.setTransferHandler(new TransferHandler() {
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            public boolean importData(TransferSupport support) {
                try {
                    List<File> files = (List<File>) support.getTransferable()
                            .getTransferData(DataFlavor.javaFileListFlavor);

                    BufferedImage img = ImageIO.read(files.get(0));
                    showProcessPage(img);
                    return true;

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Invalid file!");
                }
                return false;
            }
        });

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(dropArea);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(uploadBtn, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
    }

    // 🔥 FILE PICKER
    static BufferedImage getImageFromUser() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                return ImageIO.read(file);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error loading image!");
            }
        }
        return null;
    }

    // 🔵 PROCESS PAGE
    static void showProcessPage(BufferedImage image) {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon icon = new ImageIcon(
                image.getScaledInstance(350, 300, Image.SCALE_SMOOTH)
        );
        imageLabel.setIcon(icon);

        JPanel btnPanel = new JPanel();

        JButton convertBtn = new JButton("Convert");
        JButton printBtn = new JButton("Print");
        JButton historyBtn = new JButton("History");
        JButton exitBtn = new JButton("Exit");

        convertBtn.setFont(new Font("Arial", Font.BOLD, 16));
        printBtn.setFont(new Font("Arial", Font.BOLD, 16));
        historyBtn.setFont(new Font("Arial", Font.BOLD, 14));
        exitBtn.setFont(new Font("Arial", Font.BOLD, 14));

        btnPanel.add(convertBtn);
        btnPanel.add(printBtn);
        btnPanel.add(historyBtn);
        btnPanel.add(exitBtn);

        JLabel status = new JLabel("Ready", JLabel.CENTER);

        // 🔥 Convert
        convertBtn.addActionListener(e -> {
            status.setText("Converting...");
            status.setText("Conversion Done");
        });

        // 🔥 Print
        printBtn.addActionListener(e -> {
            status.setText("Printing...");

            // 👉 DATABASE INSERT CALL
            DBHelper.insertRecord("image.png", "SUCCESS", 1.25);

            status.setText("Printed Successfully");
        });

        // 🔥 History Button
        historyBtn.addActionListener(e -> {
            new HistoryUI().setVisible(true);
        });

        // 🔥 Exit Button
        exitBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.NORTH);
        panel.add(status, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
    }
}


