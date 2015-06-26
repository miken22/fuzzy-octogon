package ide.packages;

import ide.Properties;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;


/**
 * Class that controls a frame to get the user to select the packages to import
 * for their script. This could be extended to make each package expandable, make
 * the classes the check-boxes, rather than importing the whole package.
 * 
 * @author Michael Nowicki
 *
 */
public class JungPackageLoader extends PackageLoader {
	
	
	public JungPackageLoader(Properties properties) {
		super(properties);
	}

	/**
	 * Initialize the frame and all its components.
	 */
	public void createFrame() {
		configurationFrame = new JFrame("JUNG2 Import Settings");
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		header = new JLabel("Select imports needed for script.");
		packagePanel = new JPanel();
		packageGroup = new ArrayList<>();
				
		int rows = Packages.packages.length;
		
		configurationFrame.setSize(400, 700);
		configurationFrame.setResizable(false);
		configurationFrame.setLocationRelativeTo(null);
		configurationFrame.setLayout(null);

		header.setBounds(5, 5, 380, 45);
		header.setBackground(new Color(217, 217, 217));
		
		packagePanel.setBounds(5, 50, 380, 550);
		packagePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		packagePanel.setLayout(new GridLayout(rows,1));
		
        addPanelPackageList(Packages.packages);
        
		JScrollPane scrollPane = new JScrollPane(packagePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5, 50, 380, 550);
		
		ok.setBounds(50, 620, 100, 25);
		ok.addActionListener(new SavePackageListener(packageGroup, properties));
		cancel.setBounds(200, 620, 100, 25);
		cancel.addActionListener(new CancelListener());
		
		Container c = configurationFrame.getContentPane();
		c.add(ok);
		c.add(cancel);
		c.add(header);
		c.add(packagePanel);
		
		c.setBackground(new Color(217, 217, 217));
		
		configurationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		configurationFrame.setVisible(true);
	}

}