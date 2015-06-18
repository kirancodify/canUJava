/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netreader.view;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.netreader.measurement.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolTip;
import org.jxmapviewer.JXMapKit;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

/**
 * 
 * @author kumark2
 */
public class MainForm extends javax.swing.JFrame {

	/**
	 * Creates new form MainForm
	 */
	private static ArrayList<Measurement> master;
	private static ArrayList<Measurement> masterWlan;

	ArrayList<String> fileList = new ArrayList<String>();

	String[] opeColumns = new String[] { "MCC-MNC", "Operator",
			"Measurements/Operator" };
	DefaultTableModel tableModel = new DefaultTableModel(opeColumns, 0) {
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return Integer.class;
			default:
				return String.class;
			}
		}
	};;

	String[] devColumns = new String[] { "Id", "Brand", "Model", "OS Version",
			"Measurement/Device" };
	DefaultTableModel deviceModel = new DefaultTableModel(devColumns, 0) {
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return Integer.class;

			default:
				return String.class;
			}
		}
	};;

	String[] userColumns = new String[] { "Installation Id", "User",
			"Measurements/User" };
	DefaultTableModel userModel = new DefaultTableModel(userColumns, 0) {
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return Integer.class;
			default:
				return String.class;
			}
		}
	};;
	String[] networkColumns = new String[] { "Installation#", "User#",
			"Country", "Operator", "Latency", "Average Upload",
			"Average Download" };
	DefaultTableModel networkTableModel = new DefaultTableModel(networkColumns,
			0) {
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return Double.class;
			case 5:
				return Double.class;
			case 6:
				return Double.class;
			default:
				return String.class;
			}
		}
	};;
	String[] locationColumns = new String[] { "Source", "Lat,Long", "Time" };
	DefaultTableModel locationTabelModel = new DefaultTableModel(
			locationColumns, 0);
	String[] signalStrengthColumns = new String[] { "Value (in %)", "Time" };
	DefaultTableModel signalStrengthTableModel = new DefaultTableModel(
			signalStrengthColumns, 0);

	String[] opeColumnsW = new String[] { "IP Address", "Operator", "ISP",
			"Measurements/Operator" };
	DefaultTableModel tableModelW = new DefaultTableModel(opeColumnsW, 0) {
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return Integer.class;
			default:
				return String.class;
			}
		}
	};;
	String[] devColumnsW = new String[] { "Id", "Brand", "Model", "OS Version",
			"Measurements/Device" };
	DefaultTableModel deviceModelW = new DefaultTableModel(devColumnsW, 0) {
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return Integer.class;

			default:
				return String.class;
			}
		}
	};;
	String[] userColumnsW = new String[] { "Installation Id", "User",
			"Measurements/User" };
	DefaultTableModel userModelW = new DefaultTableModel(userColumnsW, 0) {
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return Integer.class;
			default:
				return String.class;
			}
		}
	};;
	String[] networkColumnsW = new String[] { "Sl No.", "Ticket#", "User#",
			"Country", "Operator", "Cell ID", "Average Upload",
			"Average Download" };
	DefaultTableModel networkTableModelW = new DefaultTableModel(
			networkColumnsW, 0);
	String[] locationColumnsW = new String[] { "Source", "Lat,Long", "Time" };
	DefaultTableModel locationTabelModelW = new DefaultTableModel(
			locationColumnsW, 0);
	String[] signalStrengthColumnsW = new String[] { "Value (in %)", "Time" };
	DefaultTableModel signalStrengthTableModelW = new DefaultTableModel(
			signalStrengthColumnsW, 0);
	String[] cellIdColumns = new String[] { "Area Code", "Cell Id",
			"Average Download", "Number of Measurment/Cell" };
	DefaultTableModel cellIdModel = new DefaultTableModel(cellIdColumns, 0) {
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return Float.class;
			case 3:
				return Integer.class;
			default:
				return String.class;
			}
		}
	};;

	String[] fileListColumns = new String[] { "File List" };
	DefaultTableModel fileListTableModel = new DefaultTableModel(
			fileListColumns, 0);

	DefaultListModel fileListModel = new DefaultListModel();

	// Highlighter.HighlightPainter MyHighlightPainter = new MyHighlightPainter
	// (Color.BLUE);

	public MainForm() {

		initComponents();

		setResizable(true);
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dim = new Dimension(tool.getScreenSize());
		int height = (int) dim.getHeight();
		int width = (int) dim.getWidth();
		// System.out.println(height);
		// System.out.println(width);
		setSize(width, height - 20);
		setBounds(0, 0, width, height);
		setLocation(width / 2 - getWidth() / 2, (height / 2 - 20)
				- (getHeight() / 2 - 20));
		setExtendedState(this.MAXIMIZED_BOTH);

		pack();

		jStatusBar.setText("Setting up Application Size");
		jStatusBar.setOpaque(true);
		jStatusBar.setText("Initializing Component");
		jStatusBar.setText("Setting up Application Icon");

		setIcon();
		jRadioButtonOffline.setSelected(true);

		jStatusBar.setText("Setting up Application Properties");
		jPanel1.setOpaque(false);
		jPanel3.setOpaque(false);
		jPanel2.setOpaque(false);
		jPanel4.setOpaque(false);
		jPanel6.setOpaque(false);
		jPanel7.setOpaque(false);
		jPanel8.setOpaque(false);
		jPanel9.setOpaque(false);
		jPanel10.setOpaque(false);
		jPanel11.setOpaque(false);
		jPanel12.setOpaque(false);
		jTextPanel.setOpaque(false);
		// jPanelFileList.setOpaque(false);

		jRawDataPane.setOpaque(false);
		jRawDataPane.setEditable(false);
		jTextArea1.setEditable(false);

		// jTableOperator.setOpaque(false);
		// ((DefaultTableCellRenderer)jTableOperator.getDefaultRenderer(Object.class)).setOpaque(false);
		jScrollPane3.setOpaque(false);
		jScrollPane3.getViewport().setOpaque(false);
		jTableOperator.setShowGrid(true);

		// jTableOperatorW.setOpaque(false);
		// ((DefaultTableCellRenderer)jTableOperatorW.getDefaultRenderer(Object.class)).setOpaque(false);
		jScrollPane9.setOpaque(false);
		jScrollPane9.getViewport().setOpaque(false);
		jTableOperatorW.setShowGrid(true);
		jTableOperator.setAutoCreateRowSorter(true);
		jTableUser.setAutoCreateRowSorter(true);
		jTableDevice.setAutoCreateRowSorter(true);
		jTableOperatorW.setAutoCreateRowSorter(true);
		jTableUserW.setAutoCreateRowSorter(true);
		jTableDeviceW.setAutoCreateRowSorter(true);
		jTableNetwork.setAutoCreateRowSorter(true);
		jTableCell.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				jTableOperator.getModel());
		jTableOperator.setRowSorter(sorter);

		openMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				jStatusBar.setText("Opening File");

				JFileChooser chooser = new JFileChooser(System
						.getProperty("user.home") + "\\" + "Desktop");
				// System.out.println(System.getProperty("user.home")+"\\"+"Desktop");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				int option = chooser.showOpenDialog(fileMenu);
				jStatusBar.setText("Choosing File");
				if (option == JFileChooser.APPROVE_OPTION) {
					try {
						clearApp();
						// parserChoice();
						String fileName = chooser.getSelectedFile()
								.getAbsolutePath();
						if (fileName != null) {
							if (fileName.substring(fileName.length() - 3)
									.equals("txt")
									|| fileName
											.substring(fileName.length() - 4)
											.equals("json")) {
								parser(chooser.getSelectedFile()
										.getAbsolutePath());
							} else if (fileName
									.substring(fileName.length() - 3).equals(
											"csv")) {
								System.out.println("File is CSV file");
								parseCSV(chooser.getSelectedFile()
										.getAbsolutePath());
							}
						}
						if (!fileList.contains(chooser.getSelectedFile()
								.getAbsolutePath())) {
							fileList.add(chooser.getSelectedFile()
									.getAbsolutePath());
						}
						fileListModel.clear();
						for (String fl : fileList) {
							fileListModel.addElement(fl.toString()
									.replace("[", "").replace("]", ""));
							System.out.println(fl.toString());
						}

					} catch (IOException ex) {
						Logger.getLogger(MainForm.class.getName()).log(
								Level.SEVERE, null, ex);
					}

				} else {
					System.out.println("File Not Found");
				}

			}
		});

		jClearMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				clearApp();

			}
		});

		jTableOperator.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						networkTableModel.setRowCount(0);
						locationTabelModel.setRowCount(0);
						signalStrengthTableModel.setRowCount(0);
						jNetworkTextPane.setText("");
						if (jTableOperator.getSelectedRowCount() > 0) {
							getCellList();
							if (jTableOperator.getValueAt(
									jTableOperator.getSelectedRow(), 1) == null) {
								JOptionPane.showMessageDialog(null,
										"Null Measurements for Operators");

							} else {

								String operator = jTableOperator.getValueAt(
										jTableOperator.getSelectedRow(), 1)
										.toString();
								String code = jTableOperator.getValueAt(
										jTableOperator.getSelectedRow(), 0)
										.toString();
								if (jTableUser.getSelectedRowCount() == 0) {

									if (jTableDevice.getSelectedRowCount() == 0) {
										updateDeviceModel(operator, code);
										updateUserModel(operator, code);
									} else if (jTableDevice
											.getSelectedRowCount() > 0) {
										String vendor = jTableDevice
												.getValueAt(
														jTableDevice
																.getSelectedRow(),
														1).toString();
										String model = jTableDevice.getValueAt(
												jTableDevice.getSelectedRow(),
												2).toString();
										String osVersion = jTableDevice
												.getValueAt(
														jTableDevice
																.getSelectedRow(),
														3).toString();
										updateUserModel(operator, code, vendor,
												model, osVersion);
									}
								} else if (jTableUser.getSelectedRowCount() > 0) {
									String user = jTableUser.getValueAt(
											jTableUser.getSelectedRow(), 0)
											.toString();
									if (jTableDevice.getSelectedRowCount() == 0) {
										updateDeviceModelUser(operator, code,
												user);
									}
								}
							}
						}
					}
				});

		jTableOperatorW.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (jTableOperatorW.getSelectedRowCount() > 0) {
							String ipAddress = jTableOperatorW.getValueAt(
									jTableOperatorW.getSelectedRow(), 0)
									.toString();

							if (jTableUserW.getSelectedRowCount() == 0) {

								if (jTableDeviceW.getSelectedRowCount() == 0) {
									updateDeviceModelW(ipAddress);
									updateUserModelW(ipAddress);
								} else if (jTableDeviceW.getSelectedRowCount() > 0) {
									String vendor = jTableDeviceW.getValueAt(
											jTableDeviceW.getSelectedRow(), 1)
											.toString();
									String model = jTableDeviceW.getValueAt(
											jTableDeviceW.getSelectedRow(), 2)
											.toString();
									String osVersion = jTableDevice.getValueAt(
											jTableDeviceW.getSelectedRow(), 3)
											.toString();
									updateUserModelW(ipAddress, vendor, model,
											osVersion);
								}
							} else if (jTableUserW.getSelectedRowCount() > 0) {
								String user = jTableUserW.getValueAt(
										jTableUserW.getSelectedRow(), 0)
										.toString();
								if (jTableDeviceW.getSelectedRowCount() == 0) {
									updateDeviceModelW(ipAddress, user);
								}
							}
						}

					}
				});

		jTableUser.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (jTableUser.getSelectedRowCount() > 0) {
							String user = jTableUser.getValueAt(
									jTableUser.getSelectedRow(), 0).toString();

							if (jTableOperator.getSelectedRowCount() == 0) {

								if (jTableDevice.getSelectedRowCount() == 0) {
									updateUserDeviceModel(user);
									updateUserOperatorModel(user);
								} else if (jTableDevice.getSelectedRowCount() > 0) {
									String vendor = jTableDevice.getValueAt(
											jTableDevice.getSelectedRow(), 1)
											.toString();
									String model = jTableDevice.getValueAt(
											jTableDevice.getSelectedRow(), 2)
											.toString();
									String osVersion = jTableDevice.getValueAt(
											jTableDevice.getSelectedRow(), 3)
											.toString();
									updateUserOperatorModel(user, vendor,
											model, osVersion);
								}
							} else if (jTableOperator.getSelectedRowCount() > 0) {
								String operator = jTableOperator.getValueAt(
										jTableOperator.getSelectedRow(), 1)
										.toString();
								String code = jTableOperator.getValueAt(
										jTableOperator.getSelectedRow(), 0)
										.toString();
								if (jTableDevice.getSelectedRowCount() == 0) {
									updateDeviceModelUser(operator, code, user);
								}
							}
						}

					}
				});

		jTableUserW.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (jTableUserW.getSelectedRowCount() > 0) {
							String user = jTableUserW.getValueAt(
									jTableUserW.getSelectedRow(), 0).toString();

							if (jTableOperatorW.getSelectedRowCount() == 0) {

								if (jTableDeviceW.getSelectedRowCount() == 0) {
									updateUserDeviceModelW(user);
									updateUserOperatorModelW(user);
								} else if (jTableDeviceW.getSelectedRowCount() > 0) {
									String vendor = jTableDeviceW.getValueAt(
											jTableDeviceW.getSelectedRow(), 1)
											.toString();
									String model = jTableDeviceW.getValueAt(
											jTableDeviceW.getSelectedRow(), 2)
											.toString();
									String osVersion = jTableDeviceW
											.getValueAt(
													jTableDeviceW
															.getSelectedRow(),
													3).toString();
									updateUserOperatorModelW(user, vendor,
											model, osVersion);
								}
							} else if (jTableOperatorW.getSelectedRowCount() > 0) {
								String ipAddress = jTableOperatorW.getValueAt(
										jTableOperatorW.getSelectedRow(), 0)
										.toString();
								if (jTableDeviceW.getSelectedRowCount() == 0) {
									updateDeviceModelW(ipAddress, user);
								}
							}
						}

					}
				});

		jTableDevice.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (jTableDevice.getSelectedRowCount() > 0) {
							String vendor = jTableDevice.getValueAt(
									jTableDevice.getSelectedRow(), 1)
									.toString();
							String model = jTableDevice.getValueAt(
									jTableDevice.getSelectedRow(), 2)
									.toString();
							String osVersion = jTableDevice.getValueAt(
									jTableDevice.getSelectedRow(), 3)
									.toString();

							if (jTableOperator.getSelectedRowCount() == 0) {

								if (jTableUser.getSelectedRowCount() == 0) {
									updateDeviceUserModel(vendor, model,
											osVersion);
									updateDeviceOperatorModel(vendor, model,
											osVersion);
								} else if (jTableUser.getSelectedRowCount() > 0) {
									String user = jTableUser.getValueAt(
											jTableUser.getSelectedRow(), 0)
											.toString();
									updateDeviceOperatorModel(user, vendor,
											model, osVersion);
								}
							} else if (jTableOperator.getSelectedRowCount() > 0) {
								String operator = jTableOperator.getValueAt(
										jTableOperator.getSelectedRow(), 1)
										.toString();
								if (jTableUser.getSelectedRowCount() == 0) {
									updateDeviceUserModel(operator, vendor,
											model, osVersion);
								}
							}
						}

					}
				});
		jTableLocation.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (!event.getValueIsAdjusting()) {
							if (jTableLocation.getSelectedRowCount() > 0) {
								// System.out.println("Entering here!!!!");
								// String areaCode =
								// jTableLocation.getValueAt(jTableLocation.getSelectedRow(),0).toString();
								String latlong = jTableLocation.getValueAt(
										jTableLocation.getSelectedRow(), 1)
										.toString();
								getMapOnApp(latlong);

							}

						}
					}
				});

		jTableCell.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (!event.getValueIsAdjusting()) {
							if (jTableCell.getSelectedRowCount() > 0) {
								// System.out.println("Entering here!!!!");
								String areaCode = jTableCell.getValueAt(
										jTableCell.getSelectedRow(), 0)
										.toString();
								String cellId = jTableCell.getValueAt(
										jTableCell.getSelectedRow(), 1)
										.toString();
								getAvgPerformance(areaCode, cellId);
								getNetworkData(areaCode, cellId);

							}

						}
					}
				});

		jTableDeviceW.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (jTableDeviceW.getSelectedRowCount() > 0) {
							String vendor = jTableDeviceW.getValueAt(
									jTableDeviceW.getSelectedRow(), 1)
									.toString();
							String model = jTableDeviceW.getValueAt(
									jTableDeviceW.getSelectedRow(), 2)
									.toString();
							String osVersion = jTableDeviceW.getValueAt(
									jTableDeviceW.getSelectedRow(), 3)
									.toString();

							if (jTableOperatorW.getSelectedRowCount() == 0) {

								if (jTableUserW.getSelectedRowCount() == 0) {
									updateDeviceUserModelW(vendor, model,
											osVersion);
									updateDeviceOperatorModelW(vendor, model,
											osVersion);
								} else if (jTableUserW.getSelectedRowCount() > 0) {
									String user = jTableUserW.getValueAt(
											jTableUserW.getSelectedRow(), 0)
											.toString();
									updateDeviceOperatorModelW(user, vendor,
											model, osVersion);
								}
							} else if (jTableOperatorW.getSelectedRowCount() > 0) {
								String ipAddress = jTableOperatorW.getValueAt(
										jTableOperatorW.getSelectedRow(), 0)
										.toString();
								if (jTableUserW.getSelectedRowCount() == 0) {
									updateDeviceUserModelW(ipAddress, vendor,
											model, osVersion);
								}
							}
						}

					}
				});

		jFileList.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					String filePath = jFileList.getSelectedValue().toString();
					clearApp();
					try {
						parser(filePath);
					} catch (IOException ex) {
						Logger.getLogger(MainForm.class.getName()).log(
								Level.SEVERE, null, ex);
					}
				}
			}
		});

		jTableOperator.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (jTableOperator.rowAtPoint(evt.getPoint()) >= 0
						&& evt.isControlDown()) {

					jTableOperator.clearSelection();
					jTableDevice.clearSelection();
					jTableUser.clearSelection();
					numOfOperator();
					numOfDevice();
					numOfUsers();
				}

			}
		});

		jTableOperatorW.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (jTableOperatorW.rowAtPoint(evt.getPoint()) >= 0
						&& evt.isControlDown()) {

					jTableOperatorW.clearSelection();
					jTableDeviceW.clearSelection();
					jTableUserW.clearSelection();
					numOfOperatorWlan();
					numOfDeviceWlan();
					numOfUsersWlan();
				}

			}
		});

		jTableUser.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (jTableUser.rowAtPoint(evt.getPoint()) >= 0
						&& evt.isControlDown()) {

					jTableOperator.clearSelection();
					jTableDevice.clearSelection();
					jTableUser.clearSelection();
					numOfOperator();
					numOfDevice();
					numOfUsers();
				}

			}
		});

		jTableUserW.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (jTableUserW.rowAtPoint(evt.getPoint()) >= 0
						&& evt.isControlDown()) {

					jTableOperatorW.clearSelection();
					jTableDeviceW.clearSelection();
					jTableUserW.clearSelection();
					numOfOperatorWlan();
					numOfDeviceWlan();
					numOfUsersWlan();
				}

			}
		});
		jTableDevice.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (jTableDevice.rowAtPoint(evt.getPoint()) >= 0
						&& evt.isControlDown()) {

					jTableOperator.clearSelection();
					jTableDevice.clearSelection();
					jTableUser.clearSelection();
					numOfOperator();
					numOfDevice();
					numOfUsers();
				}

			}
		});

		jTableDeviceW.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (jTableDeviceW.rowAtPoint(evt.getPoint()) >= 0
						&& evt.isControlDown()) {

					jTableOperatorW.clearSelection();
					jTableDeviceW.clearSelection();
					jTableUserW.clearSelection();
					numOfOperatorWlan();
					numOfDeviceWlan();
					numOfUsersWlan();
				}

			}
		});

		jTableNetwork.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (jTableNetwork.rowAtPoint(evt.getPoint()) >= 0
						&& evt.isControlDown()) {
					signalStrengthTableModel.setNumRows(0);
					locationTabelModel.setNumRows(0);
				}

			}
		});

		jTableNetworkW.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (jTableNetworkW.rowAtPoint(evt.getPoint()) >= 0
						&& evt.isControlDown()) {
					signalStrengthTableModelW.setNumRows(0);
					locationTabelModelW.setNumRows(0);
				}

			}
		});

		jTableNetwork.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (jTableNetwork.getSelectedRowCount() > 0) {
							if (jTableNetwork.getValueAt(
									jTableNetwork.getSelectedRow(), 0)
									.toString() != "") {
								String installationId = jTableNetwork
										.getValueAt(
												jTableNetwork.getSelectedRow(),
												0).toString();

								updateLocationData(installationId);
								updateSignalStrength(installationId);
								moreInfo(installationId);

							} else {
								signalStrengthTableModelW.setNumRows(0);
								locationTabelModelW.setNumRows(0);
							}

						}

					}
				});

		jTableNetworkW.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (jTableNetworkW.getSelectedRowCount() > 0) {
							if (jTableNetworkW.getValueAt(
									jTableNetworkW.getSelectedRow(), 1)
									.toString() != "") {
								String ticketId = jTableNetworkW.getValueAt(
										jTableNetworkW.getSelectedRow(), 1)
										.toString();

								updateLocationDataW(ticketId);
								updateSignalStrengthW(ticketId);
								moreInfoW(ticketId);

							} else {
								signalStrengthTableModelW.setNumRows(0);
								locationTabelModelW.setNumRows(0);
							}

						}

					}
				});

	}

	class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
		public MyHighlightPainter(Color color) {
			super(color);
		}
	}

	Highlighter.HighlightPainter MyHighlightPainter = new MyHighlightPainter(
			Color.BLUE);

	private void setIcon() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("netreadericon.png")));

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jPanel3 = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jRadioButtonOffline = new javax.swing.JRadioButton();
		jRadioButtononline = new javax.swing.JRadioButton();
		jPanel5 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jTextField3 = new javax.swing.JTextField();
		jTextField1 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jTextField4 = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		jTextField5 = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		jTextField6 = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		jTextField7 = new javax.swing.JTextField();
		jLabel12 = new javax.swing.JLabel();
		jTextField8 = new javax.swing.JTextField();
		jLabel13 = new javax.swing.JLabel();
		jTextField9 = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		jTextField10 = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		jTextField11 = new javax.swing.JTextField();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTableOperator = new javax.swing.JTable();
		jScrollPane4 = new javax.swing.JScrollPane();
		jTableDevice = new javax.swing.JTable();
		jScrollPane5 = new javax.swing.JScrollPane();
		jTableUser = new javax.swing.JTable();
		jOperatorList = new javax.swing.JLabel();
		jUserList = new javax.swing.JLabel();
		jDeviceList = new javax.swing.JLabel();
		jButtonGetResult = new javax.swing.JButton();
		jButtonClearAll = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jTextField12 = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		jTextField13 = new javax.swing.JTextField();
		jPanel6 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTableNetwork = new javax.swing.JTable();
		jLabel8 = new javax.swing.JLabel();
		jScrollPane6 = new javax.swing.JScrollPane();
		jTableLocation = new javax.swing.JTable();
		jLabel16 = new javax.swing.JLabel();
		jScrollPane7 = new javax.swing.JScrollPane();
		jTableSignalStrength = new javax.swing.JTable();
		jLabel17 = new javax.swing.JLabel();
		jScrollPane8 = new javax.swing.JScrollPane();
		jNetworkTextPane = new javax.swing.JTextPane();
		jLabel18 = new javax.swing.JLabel();
		jScrollPane18 = new javax.swing.JScrollPane();
		jTableCell = new javax.swing.JTable();
		jPanel7 = new javax.swing.JPanel();
		jTextField14 = new javax.swing.JTextField();
		jTextField15 = new javax.swing.JTextField();
		jLabel20 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		jTextField16 = new javax.swing.JTextField();
		jLabel22 = new javax.swing.JLabel();
		jLabel23 = new javax.swing.JLabel();
		jTextField17 = new javax.swing.JTextField();
		jLabel24 = new javax.swing.JLabel();
		jTextField18 = new javax.swing.JTextField();
		jLabel25 = new javax.swing.JLabel();
		jTextField19 = new javax.swing.JTextField();
		jLabel26 = new javax.swing.JLabel();
		jTextField20 = new javax.swing.JTextField();
		jLabel27 = new javax.swing.JLabel();
		jTextField21 = new javax.swing.JTextField();
		jLabel28 = new javax.swing.JLabel();
		jTextField22 = new javax.swing.JTextField();
		jLabel29 = new javax.swing.JLabel();
		jTextField23 = new javax.swing.JTextField();
		jLabel30 = new javax.swing.JLabel();
		jTextField24 = new javax.swing.JTextField();
		jPanel9 = new javax.swing.JPanel();
		jScrollPane9 = new javax.swing.JScrollPane();
		jTableOperatorW = new javax.swing.JTable();
		jScrollPane10 = new javax.swing.JScrollPane();
		jTableDeviceW = new javax.swing.JTable();
		jScrollPane11 = new javax.swing.JScrollPane();
		jTableUserW = new javax.swing.JTable();
		jOperatorList1 = new javax.swing.JLabel();
		jUserList1 = new javax.swing.JLabel();
		jDeviceList1 = new javax.swing.JLabel();
		jButtonGetResultWlan = new javax.swing.JButton();
		jButtonClearAllWlan = new javax.swing.JButton();
		jLabel31 = new javax.swing.JLabel();
		jTextField25 = new javax.swing.JTextField();
		jLabel32 = new javax.swing.JLabel();
		jTextField26 = new javax.swing.JTextField();
		jPanel10 = new javax.swing.JPanel();
		jScrollPane12 = new javax.swing.JScrollPane();
		jTableNetworkW = new javax.swing.JTable();
		jLabel33 = new javax.swing.JLabel();
		jScrollPane13 = new javax.swing.JScrollPane();
		jTableLocationW = new javax.swing.JTable();
		jLabel34 = new javax.swing.JLabel();
		jScrollPane14 = new javax.swing.JScrollPane();
		jTableSignalStrengthW = new javax.swing.JTable();
		jLabel35 = new javax.swing.JLabel();
		jScrollPane15 = new javax.swing.JScrollPane();
		jNetworkTextPaneW = new javax.swing.JTextPane();
		jLabel36 = new javax.swing.JLabel();
		jPanel11 = new javax.swing.JPanel();
		jScrollPane16 = new javax.swing.JScrollPane();
		jRawDataPane = new javax.swing.JTextPane();
		jSearchText = new javax.swing.JTextField();
		jSearchButton = new javax.swing.JButton();
		jPanel12 = new javax.swing.JPanel();
		jTextPanel = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		jLabel37 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jScrollPane17 = new javax.swing.JScrollPane();
		jFileList = new javax.swing.JList();
		jStatusBar = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		openMenuItem = new javax.swing.JMenuItem();
		saveMenuItem = new javax.swing.JMenuItem();
		saveAsMenuItem = new javax.swing.JMenuItem();
		jClearMenu = new javax.swing.JMenuItem();
		jMenuItem1 = new javax.swing.JMenuItem();
		exitMenuItem = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();
		cutMenuItem = new javax.swing.JMenuItem();
		copyMenuItem = new javax.swing.JMenuItem();
		pasteMenuItem = new javax.swing.JMenuItem();
		deleteMenuItem = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		contentsMenuItem = new javax.swing.JMenuItem();
		aboutMenuItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("netreader Analytical Tool");
		setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight());
		getContentPane().setLayout(null);

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Controls"));
		jPanel1.setAutoscrolls(true);

		jButton1.setText("Mobile Overview");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("WLAN Overview");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Raw Data");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jRadioButtonOffline.setText("Offline Mode");
		jRadioButtonOffline
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonOfflineActionPerformed(evt);
					}
				});

		jRadioButtononline.setText("Online Mode");
		jRadioButtononline
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtononlineActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				14,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				184,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addGap(14,
																												14,
																												14)
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																jButton1,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																184,
																																Short.MAX_VALUE)
																														.addComponent(
																																jButton3,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)))
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addGap(46,
																												46,
																												46)
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																jRadioButtonOffline,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																120,
																																Short.MAX_VALUE)
																														.addComponent(
																																jRadioButtononline,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))))
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(14, 14, 14)
										.addComponent(jButton1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButton2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												9, Short.MAX_VALUE)
										.addComponent(jButton3)
										.addGap(77, 77, 77)
										.addComponent(jRadioButtonOffline)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jRadioButtononline,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		jPanel5.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0,
				Short.MAX_VALUE));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 198,
				Short.MAX_VALUE));

		jPanel8.setLayout(new java.awt.CardLayout());

		jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Mobile Overview"));
		jPanel2.setAutoscrolls(true);
		jPanel2.setPreferredSize(new java.awt.Dimension(1092, 930));

		jTextField3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField3ActionPerformed(evt);
			}
		});

		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jLabel1.setText("Total Measurements:");

		jLabel2.setText("Number of Users:");

		jTextField2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField2ActionPerformed(evt);
			}
		});

		jLabel4.setText("Number of Operator:");

		jLabel5.setText("File Path:");

		jTextField4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField4ActionPerformed(evt);
			}
		});

		jLabel9.setText("Number of Device:");

		jTextField5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField5ActionPerformed(evt);
			}
		});

		jLabel10.setText("Maximum Upload Avg:");

		jTextField6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField6ActionPerformed(evt);
			}
		});

		jLabel11.setText("Minimum Upload Avg:");

		jLabel12.setText("Maximum Latency: ");

		jTextField8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField8ActionPerformed(evt);
			}
		});

		jLabel13.setText("Minimum Latency:");

		jTextField9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField9ActionPerformed(evt);
			}
		});

		jLabel14.setText("#Mobile Meaurements:");

		jTextField10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField10ActionPerformed(evt);
			}
		});

		jLabel15.setText("#Wlan Measurements: ");

		jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(
				0, 0, 0), 2, true));

		jTableOperator.setModel(tableModel);
		jTableOperator.setName("Operator List"); // NOI18N
		jScrollPane3.setViewportView(jTableOperator);

		jTableDevice.setModel(deviceModel);
		jScrollPane4.setViewportView(jTableDevice);

		jTableUser.setModel(userModel);
		jScrollPane5.setViewportView(jTableUser);

		jOperatorList.setText("Operator List:");

		jUserList.setText("User List:");

		jDeviceList.setText("Device List:");

		jButtonGetResult.setText("Get Results");
		jButtonGetResult.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGetResultActionPerformed(evt);
			}
		});

		jButtonClearAll.setText("Clear All");
		jButtonClearAll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonClearAllActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addComponent(
																				jButtonGetResult)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButtonClearAll,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				75,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane3,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								285,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jOperatorList,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								121,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane5,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								263,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jUserList,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								84,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jDeviceList,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								90,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jScrollPane4,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								422,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel4Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jOperatorList)
																		.addComponent(
																				jUserList,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				23,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addGap(4,
																				4,
																				4)
																		.addComponent(
																				jDeviceList,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				17,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jScrollPane4,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																205,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE))
										.addGap(11, 11, 11)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonGetResult,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jButtonClearAll))));

		jLabel3.setText("Minimum Download Avg:");

		jTextField12.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField12ActionPerformed(evt);
			}
		});

		jLabel6.setText("Maximum Download Avg:");

		jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(
				0, 0, 0), 2, true));
		jPanel6.setPreferredSize(new java.awt.Dimension(1032, 400));

		jTableNetwork.setModel(networkTableModel);
		jScrollPane2.setViewportView(jTableNetwork);

		jLabel8.setText("Base Station Data:");

		jTableLocation.setModel(locationTabelModel);
		jScrollPane6.setViewportView(jTableLocation);

		jLabel16.setText("Location:");

		jTableSignalStrength.setModel(signalStrengthTableModel);
		jScrollPane7.setViewportView(jTableSignalStrength);

		jLabel17.setText("Signal Strength:");

		jScrollPane8.setViewportView(jNetworkTextPane);

		jLabel18.setText("More Info:");

		jTableCell.setModel(cellIdModel);
		jScrollPane18.setViewportView(jTableCell);

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel6Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jLabel16,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								75,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jScrollPane6,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								280,
																								Short.MAX_VALUE)
																						.addComponent(
																								jScrollPane18,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								0,
																								Short.MAX_VALUE))
																		.addGap(26,
																				26,
																				26)
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								jPanel6Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel6Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel17,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																106,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jScrollPane7,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																363,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(18,
																												18,
																												18)
																										.addGroup(
																												jPanel6Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel18,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																103,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jScrollPane8,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																299,
																																javax.swing.GroupLayout.PREFERRED_SIZE)))
																						.addComponent(
																								jScrollPane2))
																		.addGap(660,
																				660,
																				660))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel8,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				132,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jLabel8,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												21,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																149,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane18,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE))
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel6Layout
																										.createSequentialGroup()
																										.addGap(18,
																												18,
																												18)
																										.addComponent(
																												jLabel17))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								jPanel6Layout
																										.createSequentialGroup()
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jLabel18)))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane8,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								143,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jScrollPane7,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								142,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jLabel16,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				22,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jScrollPane6,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				139,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(104, 104, 104)));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGap(91,
																				91,
																				91)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel2Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel5,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												78,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField4,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												498,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																jPanel2Layout
																																		.createSequentialGroup()
																																		.addGroup(
																																				jPanel2Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addGroup(
																																								jPanel2Layout
																																										.createSequentialGroup()
																																										.addComponent(
																																												jLabel14)
																																										.addGap(20,
																																												20,
																																												20)
																																										.addGroup(
																																												jPanel2Layout
																																														.createParallelGroup(
																																																javax.swing.GroupLayout.Alignment.LEADING)
																																														.addComponent(
																																																jTextField3,
																																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																																69,
																																																javax.swing.GroupLayout.PREFERRED_SIZE)
																																														.addComponent(
																																																jTextField10,
																																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																																69,
																																																javax.swing.GroupLayout.PREFERRED_SIZE)))
																																						.addComponent(
																																								jLabel4,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								126,
																																								javax.swing.GroupLayout.PREFERRED_SIZE))
																																		.addGroup(
																																				jPanel2Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addGroup(
																																								jPanel2Layout
																																										.createSequentialGroup()
																																										.addGap(42,
																																												42,
																																												42)
																																										.addComponent(
																																												jLabel15))
																																						.addGroup(
																																								jPanel2Layout
																																										.createSequentialGroup()
																																										.addGap(40,
																																												40,
																																												40)
																																										.addComponent(
																																												jLabel2,
																																												javax.swing.GroupLayout.PREFERRED_SIZE,
																																												112,
																																												javax.swing.GroupLayout.PREFERRED_SIZE))))
																														.addGroup(
																																jPanel2Layout
																																		.createSequentialGroup()
																																		.addGroup(
																																				jPanel2Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(
																																								jLabel12)
																																						.addComponent(
																																								jLabel10))
																																		.addGap(24,
																																				24,
																																				24)
																																		.addGroup(
																																				jPanel2Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING,
																																								false)
																																						.addComponent(
																																								jTextField8)
																																						.addComponent(
																																								jTextField6,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								69,
																																								javax.swing.GroupLayout.PREFERRED_SIZE))
																																		.addGroup(
																																				jPanel2Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addGroup(
																																								jPanel2Layout
																																										.createSequentialGroup()
																																										.addGap(40,
																																												40,
																																												40)
																																										.addComponent(
																																												jLabel13,
																																												javax.swing.GroupLayout.PREFERRED_SIZE,
																																												112,
																																												javax.swing.GroupLayout.PREFERRED_SIZE))
																																						.addGroup(
																																								jPanel2Layout
																																										.createSequentialGroup()
																																										.addGap(37,
																																												37,
																																												37)
																																										.addComponent(
																																												jLabel3,
																																												javax.swing.GroupLayout.PREFERRED_SIZE,
																																												136,
																																												javax.swing.GroupLayout.PREFERRED_SIZE)))))
																										.addGap(10,
																												10,
																												10)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																jPanel2Layout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				false)
																																		.addComponent(
																																				jTextField12)
																																		.addComponent(
																																				jTextField9,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				78,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addComponent(
																																jTextField11,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																78,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jTextField2,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																78,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(18,
																												18,
																												18)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																jLabel11,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel1,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel9,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																121,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jLabel6))
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																jPanel2Layout
																																		.createSequentialGroup()
																																		.addGap(18,
																																				18,
																																				18)
																																		.addComponent(
																																				jTextField7,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				73,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																jPanel2Layout
																																		.createSequentialGroup()
																																		.addGap(18,
																																				18,
																																				18)
																																		.addGroup(
																																				jPanel2Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(
																																								jTextField5,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								73,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addComponent(
																																								jTextField13,
																																								javax.swing.GroupLayout.Alignment.TRAILING,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								73,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addComponent(
																																								jTextField1,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								73,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)))))))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGap(63,
																				63,
																				63)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jPanel6,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jPanel4,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(86, Short.MAX_VALUE)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																jTextField4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jTextField11,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTextField2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								21,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(2,
																				2,
																				2)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTextField9)
																						.addComponent(
																								jLabel13,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTextField12,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel3,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								26,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addGap(3,
																												3,
																												3)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																jLabel14,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																23,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jTextField10,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jLabel15,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																23,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addGroup(
																																jPanel2Layout
																																		.createSequentialGroup()
																																		.addGap(3,
																																				3,
																																				3)
																																		.addComponent(
																																				jTextField3,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addComponent(
																																jLabel4,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																jTextField8)
																														.addComponent(
																																jLabel12,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addGap(1,
																												1,
																												1)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																jPanel2Layout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.BASELINE)
																																		.addComponent(
																																				jLabel6,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				19,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addComponent(
																																				jTextField13,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																jPanel2Layout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.BASELINE)
																																		.addComponent(
																																				jTextField6,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addComponent(
																																				jLabel10))))
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																jLabel1,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																20,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jTextField1,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																jTextField5,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jLabel9,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																20,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																jTextField7,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jLabel11))))
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addComponent(
												jPanel4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jPanel6,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												410,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(151, Short.MAX_VALUE)));

		jPanel8.add(jPanel2, "card2");

		jPanel7.setBorder(javax.swing.BorderFactory
				.createTitledBorder("WLAN Overview"));
		jPanel7.setAutoscrolls(true);
		jPanel7.setPreferredSize(new java.awt.Dimension(1092, 930));

		jTextField14.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField14ActionPerformed(evt);
			}
		});

		jTextField15.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField15ActionPerformed(evt);
			}
		});

		jLabel20.setText("Total Measurements:");

		jLabel21.setText("Number of Users:");

		jTextField16.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField16ActionPerformed(evt);
			}
		});

		jLabel22.setText("Number of Operator:");

		jLabel23.setText("File Path:");

		jTextField17.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField17ActionPerformed(evt);
			}
		});

		jLabel24.setText("Number of Device:");

		jTextField18.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField18ActionPerformed(evt);
			}
		});

		jLabel25.setText("Maximum Upload Avg:");

		jTextField19.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField19ActionPerformed(evt);
			}
		});

		jLabel26.setText("Minimum Upload Avg:");

		jLabel27.setText("Maximum Latency: ");

		jTextField21.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField21ActionPerformed(evt);
			}
		});

		jLabel28.setText("Minimum Latency:");

		jTextField22.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField22ActionPerformed(evt);
			}
		});

		jLabel29.setText("#Mobile Meaurements:");

		jTextField23.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField23ActionPerformed(evt);
			}
		});

		jLabel30.setText("#Wlan Measurements: ");

		jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(
				0, 0, 0), 2, true));

		jTableOperatorW.setModel(tableModelW);
		jTableOperatorW.setName("Operator List"); // NOI18N
		jScrollPane9.setViewportView(jTableOperatorW);

		jTableDeviceW.setModel(deviceModelW);
		jScrollPane10.setViewportView(jTableDeviceW);

		jTableUserW.setModel(userModelW);
		jScrollPane11.setViewportView(jTableUserW);

		jOperatorList1.setText("Operator List:");

		jUserList1.setText("User List:");

		jDeviceList1.setText("Device List:");

		jButtonGetResultWlan.setText("Get Results");
		jButtonGetResultWlan
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonGetResultWlanActionPerformed(evt);
					}
				});

		jButtonClearAllWlan.setText("Clear All");
		jButtonClearAllWlan
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonClearAllWlanActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(
				jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addComponent(
																				jButtonGetResultWlan)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButtonClearAllWlan,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				75,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel9Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane9,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								285,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jOperatorList1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								121,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel9Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane11,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								263,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jUserList1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								84,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel9Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jDeviceList1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								90,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jScrollPane10,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								422,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel9Layout
				.setVerticalGroup(jPanel9Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel9Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jOperatorList1)
																		.addComponent(
																				jUserList1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				23,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addGap(4,
																				4,
																				4)
																		.addComponent(
																				jDeviceList1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				17,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jScrollPane10,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																205,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane11,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane9,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonGetResultWlan)
														.addComponent(
																jButtonClearAllWlan))
										.addGap(0, 9, Short.MAX_VALUE)));

		jLabel31.setText("Minimum Download Avg:");

		jTextField25.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField25ActionPerformed(evt);
			}
		});

		jLabel32.setText("Maximum Download Avg:");

		jPanel10.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 0, 0), 2, true));
		jPanel10.setPreferredSize(new java.awt.Dimension(1032, 400));

		jTableNetworkW.setModel(networkTableModelW);
		jScrollPane12.setViewportView(jTableNetworkW);

		jLabel33.setText("Base Station Data:");

		jTableLocationW.setModel(locationTabelModelW);
		jScrollPane13.setViewportView(jTableLocationW);

		jLabel34.setText("Location:");

		jTableSignalStrengthW.setModel(signalStrengthTableModelW);
		jScrollPane14.setViewportView(jTableSignalStrengthW);

		jLabel35.setText("Signal Strength:");

		jScrollPane15.setViewportView(jNetworkTextPaneW);

		jLabel36.setText("More Info:");

		javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(
				jPanel10);
		jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout
				.setHorizontalGroup(jPanel10Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel10Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel10Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel10Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel10Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel10Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel34,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												75,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(223,
																												223,
																												223))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								jPanel10Layout
																										.createSequentialGroup()
																										.addComponent(
																												jScrollPane13,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												280,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
																		.addGroup(
																				jPanel10Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane14,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								363,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel35,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								106,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel10Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel10Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel36,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												103,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(0,
																												0,
																												Short.MAX_VALUE))
																						.addComponent(
																								jScrollPane15)))
														.addGroup(
																jPanel10Layout
																		.createSequentialGroup()
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)
																		.addGroup(
																				jPanel10Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel33,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								132,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jScrollPane12,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								997,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGap(21, 21, 21)));
		jPanel10Layout
				.setVerticalGroup(jPanel10Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel10Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jLabel33,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												21,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jScrollPane12,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												149,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel10Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jPanel10Layout
																		.createSequentialGroup()
																		.addGap(4,
																				4,
																				4)
																		.addGroup(
																				jPanel10Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel35,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jLabel36)))
														.addComponent(
																jLabel34,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel10Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jScrollPane15,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																139,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane14,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane13,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE))
										.addGap(34, 34, 34)));

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
				jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addGap(90,
																				90,
																				90)
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel7Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel7Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																jPanel7Layout
																																		.createSequentialGroup()
																																		.addGroup(
																																				jPanel7Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(
																																								jLabel27)
																																						.addComponent(
																																								jLabel25))
																																		.addGap(24,
																																				24,
																																				24)
																																		.addGroup(
																																				jPanel7Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING,
																																								false)
																																						.addComponent(
																																								jTextField21)
																																						.addComponent(
																																								jTextField19,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								69,
																																								javax.swing.GroupLayout.PREFERRED_SIZE))
																																		.addGap(42,
																																				42,
																																				42)
																																		.addGroup(
																																				jPanel7Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(
																																								jLabel31)
																																						.addComponent(
																																								jLabel28,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								104,
																																								javax.swing.GroupLayout.PREFERRED_SIZE))
																																		.addGap(10,
																																				10,
																																				10)
																																		.addGroup(
																																				jPanel7Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(
																																								jTextField22,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								78,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addComponent(
																																								jTextField25,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								78,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)))
																														.addComponent(
																																jLabel22,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																126,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(45,
																												45,
																												45)
																										.addGroup(
																												jPanel7Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																jLabel26,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel20,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel24,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																121,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jLabel32))
																										.addGroup(
																												jPanel7Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																jPanel7Layout
																																		.createSequentialGroup()
																																		.addGap(18,
																																				18,
																																				18)
																																		.addComponent(
																																				jTextField20,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				73,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																jPanel7Layout
																																		.createSequentialGroup()
																																		.addGap(18,
																																				18,
																																				18)
																																		.addGroup(
																																				jPanel7Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(
																																								jTextField18,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								73,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addComponent(
																																								jTextField15,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								73,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addComponent(
																																								jTextField26,
																																								javax.swing.GroupLayout.Alignment.TRAILING,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								73,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)))))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel7Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel29)
																										.addGap(20,
																												20,
																												20)
																										.addGroup(
																												jPanel7Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jTextField14,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																69,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jTextField23,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																69,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(42,
																												42,
																												42)
																										.addGroup(
																												jPanel7Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																jLabel30,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel21,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																112,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(14,
																												14,
																												14)
																										.addGroup(
																												jPanel7Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jTextField24,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																78,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jTextField16,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																78,
																																javax.swing.GroupLayout.PREFERRED_SIZE)))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel7Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel23,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												78,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField17,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												498,
																												javax.swing.GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addGap(67,
																				67,
																				67)
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jPanel10,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jPanel9,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(82, Short.MAX_VALUE)));
		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel23)
														.addComponent(
																jTextField17,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(3, 3, 3)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel29,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								23,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jTextField23,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel30,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								23,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jTextField24,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel7Layout
																										.createSequentialGroup()
																										.addGap(3,
																												3,
																												3)
																										.addComponent(
																												jTextField14,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanel7Layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.BASELINE)
																										.addComponent(
																												jTextField16,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												jLabel21))
																						.addComponent(
																								jLabel22,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								23,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel7Layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.BASELINE)
																										.addComponent(
																												jTextField21,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												jTextField22,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								jLabel27,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel28,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel20,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jTextField15,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTextField18,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel24,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTextField20,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel26,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								20,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGap(4, 4, 4)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel7Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabel32,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				19,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jTextField26,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel7Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabel31,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				20,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jTextField25,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jTextField19,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jLabel25)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel9,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel10,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												408,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(152, Short.MAX_VALUE)));

		jPanel8.add(jPanel7, "card2");

		jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Parsed Data",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Arial", 1, 12), java.awt.Color.black)); // NOI18N

		jScrollPane16.setViewportView(jRawDataPane);

		jSearchButton.setText("Search");
		jSearchButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jSearchButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(
				jPanel11);
		jPanel11.setLayout(jPanel11Layout);
		jPanel11Layout
				.setHorizontalGroup(jPanel11Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel11Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel11Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane16,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																1161,
																Short.MAX_VALUE)
														.addGroup(
																jPanel11Layout
																		.createSequentialGroup()
																		.addComponent(
																				jSearchText,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				346,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jSearchButton)
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanel11Layout
				.setVerticalGroup(jPanel11Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel11Layout
										.createSequentialGroup()
										.addContainerGap(12, Short.MAX_VALUE)
										.addGroup(
												jPanel11Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jSearchButton,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jSearchText))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jScrollPane16,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												925,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		jPanel8.add(jPanel11, "card4");

		javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(
				jPanel12);
		jPanel12.setLayout(jPanel12Layout);
		jPanel12Layout.setHorizontalGroup(jPanel12Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1193,
				Short.MAX_VALUE));
		jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1006,
				Short.MAX_VALUE));

		jPanel8.add(jPanel12, "card5");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jPanel5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jPanel8,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												1193,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(1085, 1085, 1085)));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel5,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(740, 740, 740))
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jPanel8,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												1006,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		getContentPane().add(jPanel3);
		jPanel3.setBounds(10, 10, 1440, 1020);

		jLabel7.setText("Results:");

		jLabel37.setText("File History:");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jFileList.setModel(fileListModel);
		jScrollPane17.setViewportView(jFileList);

		javax.swing.GroupLayout jTextPanelLayout = new javax.swing.GroupLayout(
				jTextPanel);
		jTextPanel.setLayout(jTextPanelLayout);
		jTextPanelLayout
				.setHorizontalGroup(jTextPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jTextPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jTextPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane17,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																370,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane1)
														.addGroup(
																jTextPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				jTextPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel7,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								110,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel37,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								65,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jTextPanelLayout
				.setVerticalGroup(jTextPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jTextPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jLabel7,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												30,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												447,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jLabel37)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane17,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												175,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(308, Short.MAX_VALUE)));

		getContentPane().add(jTextPanel);
		jTextPanel.setBounds(1470, 10, 390, 1020);

		jStatusBar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
		getContentPane().add(jStatusBar);
		jStatusBar.setBounds(0, 1100, 1880, 20);

		jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/org/netreader/view/netreaderbg.jpg"))); // NOI18N
		jLabel19.setText("jLabel19");
		jLabel19.setAutoscrolls(true);
		getContentPane().add(jLabel19);
		jLabel19.setBounds(0, 0, 1950, 1130);

		fileMenu.setMnemonic('f');
		fileMenu.setText("File");

		openMenuItem.setMnemonic('o');
		openMenuItem.setText("Open");
		fileMenu.add(openMenuItem);

		saveMenuItem.setMnemonic('s');
		saveMenuItem.setText("Save");
		fileMenu.add(saveMenuItem);

		saveAsMenuItem.setMnemonic('a');
		saveAsMenuItem.setText("Save As ...");
		saveAsMenuItem.setDisplayedMnemonicIndex(5);
		fileMenu.add(saveAsMenuItem);

		jClearMenu.setText("Clear");
		jClearMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jClearMenuActionPerformed(evt);
			}
		});
		fileMenu.add(jClearMenu);

		jMenuItem1.setText("Export");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		fileMenu.add(jMenuItem1);

		exitMenuItem.setMnemonic('x');
		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		editMenu.setMnemonic('e');
		editMenu.setText("Edit");

		cutMenuItem.setMnemonic('t');
		cutMenuItem.setText("Cut");
		editMenu.add(cutMenuItem);

		copyMenuItem.setMnemonic('y');
		copyMenuItem.setText("Copy");
		editMenu.add(copyMenuItem);

		pasteMenuItem.setMnemonic('p');
		pasteMenuItem.setText("Paste");
		editMenu.add(pasteMenuItem);

		deleteMenuItem.setMnemonic('d');
		deleteMenuItem.setText("Delete");
		editMenu.add(deleteMenuItem);

		menuBar.add(editMenu);

		helpMenu.setMnemonic('h');
		helpMenu.setText("Help");

		contentsMenuItem.setMnemonic('c');
		contentsMenuItem.setText("Contents");
		helpMenu.add(contentsMenuItem);

		aboutMenuItem.setMnemonic('a');
		aboutMenuItem.setText("About");
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		getAccessibleContext().setAccessibleName("netreader Analatycal Tool");

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitMenuItemActionPerformed
		System.exit(0);
	}// GEN-LAST:event_exitMenuItemActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		// REMOVE PANEL
		jPanel8.removeAll();
		jPanel8.repaint();
		jPanel8.revalidate();

		// add panel
		jPanel8.add(jPanel2);
		jPanel8.repaint();
		jPanel8.revalidate();
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jClearMenuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jClearMenuActionPerformed
		// TODO add your handling code here:
		master.clear();
		jPanel2.repaint();

		masterWlan.clear();
		jPanel7.repaint();

	}// GEN-LAST:event_jClearMenuActionPerformed

	private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField14ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField14ActionPerformed

	private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField15ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField15ActionPerformed

	private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField16ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField16ActionPerformed

	private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField17ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField17ActionPerformed

	private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField18ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField18ActionPerformed

	private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField19ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField19ActionPerformed

	private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField21ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField21ActionPerformed

	private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField22ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField22ActionPerformed

	private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField23ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField23ActionPerformed

	private void jButtonGetResultWlanActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonGetResultWlanActionPerformed
		// TODO add your handling code here:
		jTextArea1.setText(null);

		if (jTableOperatorW.getSelectedRowCount() > 0
				&& jTableUserW.getSelectedRowCount() == 0
				&& jTableDeviceW.getSelectedRowCount() == 0) {
			String ipAddress = jTableOperatorW.getValueAt(
					jTableOperatorW.getSelectedRow(), 0).toString();
			getSpecificDataOperatorW(ipAddress);
		}
		if (jTableOperatorW.getSelectedRowCount() == 0
				&& jTableUserW.getSelectedRowCount() > 0
				&& jTableDeviceW.getSelectedRowCount() == 0) {
			String installationId = jTableUserW.getValueAt(
					jTableUserW.getSelectedRow(), 0).toString();
			getSpecificDataUserW(installationId);
		}
		if (jTableOperatorW.getSelectedRowCount() == 0
				&& jTableUserW.getSelectedRowCount() == 0
				&& jTableDeviceW.getSelectedRowCount() > 0) {
			String vendor = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 1).toString();
			String model = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 2).toString();
			String osVersion = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 3).toString();
			getSpecificDataDeviceW(vendor, model, osVersion);
		}
		if (jTableOperatorW.getSelectedRowCount() > 0
				&& jTableUserW.getSelectedRowCount() > 0
				&& jTableDeviceW.getSelectedRowCount() == 0) {
			String ipAddress = jTableOperatorW.getValueAt(
					jTableOperatorW.getSelectedRow(), 0).toString();
			String installationId = jTableUserW.getValueAt(
					jTableUserW.getSelectedRow(), 0).toString();
			getSpecificDataOpeUserW(ipAddress, installationId);
		}
		if (jTableOperatorW.getSelectedRowCount() > 0
				&& jTableUserW.getSelectedRowCount() == 0
				&& jTableDeviceW.getSelectedRowCount() > 0) {
			String ipAddress = jTableOperatorW.getValueAt(
					jTableOperatorW.getSelectedRow(), 0).toString();
			String vendor = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 1).toString();
			String model = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 2).toString();
			String osVersion = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 3).toString();
			getSpecificDataOpeDevW(ipAddress, vendor, model, osVersion);
		}
		if (jTableOperatorW.getSelectedRowCount() == 0
				&& jTableUserW.getSelectedRowCount() > 0
				&& jTableDeviceW.getSelectedRowCount() > 0) {
			String installationId = jTableUserW.getValueAt(
					jTableUserW.getSelectedRow(), 0).toString();
			String vendor = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 1).toString();
			String model = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 2).toString();
			String osVersion = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 3).toString();
			getSpecificDataUseDevW(installationId, vendor, model, osVersion);
		}
		if (jTableOperatorW.getSelectedRowCount() > 0
				&& jTableUserW.getSelectedRowCount() > 0
				&& jTableDeviceW.getSelectedRowCount() > 0) {
			String ipAddress = jTableOperatorW.getValueAt(
					jTableOperatorW.getSelectedRow(), 0).toString();
			String installationId = jTableUserW.getValueAt(
					jTableUserW.getSelectedRow(), 0).toString();
			String vendor = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 1).toString();
			String model = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 2).toString();
			String osVersion = jTableDeviceW.getValueAt(
					jTableDeviceW.getSelectedRow(), 3).toString();
			getSpecificDataW(ipAddress, installationId, vendor, model,
					osVersion);
		}
		if (jTableOperatorW.getSelectedRowCount() == 0
				&& jTableUserW.getSelectedRowCount() == 0
				&& jTableDeviceW.getSelectedRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Please select an option");
		}
	}// GEN-LAST:event_jButtonGetResultWlanActionPerformed

	private void jButtonClearAllWlanActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonClearAllWlanActionPerformed
		// TODO add your handling code here:
		jTableOperatorW.clearSelection();
		jTableDeviceW.clearSelection();
		jTableUserW.clearSelection();
		jTextArea1.setText("");
		numOfOperatorWlan();
		numOfDeviceWlan();
		numOfUsersWlan();
	}// GEN-LAST:event_jButtonClearAllWlanActionPerformed

	private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField25ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField25ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		// TODO add your handling code here:

		// REMOVE PANEL
		jPanel8.removeAll();
		jPanel8.repaint();
		jPanel8.revalidate();

		// add panel
		jPanel8.add(jPanel7);
		jPanel8.repaint();
		jPanel8.revalidate();

	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		// TODO add your handling code here:
		// REMOVE PANEL
		jPanel8.removeAll();
		jPanel8.repaint();
		jPanel8.revalidate();

		// add panel
		jPanel8.add(jPanel11);
		jPanel8.repaint();
		jPanel8.revalidate();

		// if (jRawDataPane.getText().length()==0 ||
		// jRawDataPane.getText().equals(" ")){
		if (master.size() > 0 || masterWlan.size() > 0) {
			if (jRawDataPane.getText().length() == 0
					|| jRawDataPane.getText().equals(" ")) {
				System.out.println("Do not enter here");
				getRawDataPane();
				jRawDataPane.setEditable(false);
			}
		}

	}// GEN-LAST:event_jButton3ActionPerformed

	private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField12ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField12ActionPerformed

	private void jButtonClearAllActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonClearAllActionPerformed
		// TODO add your handling code here:
		jTableOperator.clearSelection();
		jTableDevice.clearSelection();
		jTableUser.clearSelection();
		jTableCell.clearSelection();
		jTextArea1.setText("");
		cellIdModel.setRowCount(0);
		numOfOperator();
		numOfDevice();
		numOfUsers();
	}// GEN-LAST:event_jButtonClearAllActionPerformed

	private void jButtonGetResultActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonGetResultActionPerformed
		// TODO add your handling code here:
		jTextArea1.setText(null);

		if (jTableOperator.getSelectedRowCount() > 0
				&& jTableUser.getSelectedRowCount() == 0
				&& jTableDevice.getSelectedRowCount() == 0) {
			String operator = jTableOperator.getValueAt(
					jTableOperator.getSelectedRow(), 1).toString();
			String code = jTableOperator.getValueAt(
					jTableOperator.getSelectedRow(), 0).toString();
			getSpecificDataOperator(operator, code);
		}
		if (jTableOperator.getSelectedRowCount() == 0
				&& jTableUser.getSelectedRowCount() > 0
				&& jTableDevice.getSelectedRowCount() == 0) {
			String user = jTableUser.getValueAt(jTableUser.getSelectedRow(), 0)
					.toString();
			getSpecificDataUser(user);
		}
		if (jTableOperator.getSelectedRowCount() == 0
				&& jTableUser.getSelectedRowCount() == 0
				&& jTableDevice.getSelectedRowCount() > 0) {
			String vendor = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 1).toString();
			String model = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 2).toString();
			String osVersion = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 3).toString();
			getSpecificDataDevice(vendor, model, osVersion);
		}
		if (jTableOperator.getSelectedRowCount() > 0
				&& jTableUser.getSelectedRowCount() > 0
				&& jTableDevice.getSelectedRowCount() == 0) {
			String operator = jTableOperator.getValueAt(
					jTableOperator.getSelectedRow(), 1).toString();
			String code = jTableOperator.getValueAt(
					jTableOperator.getSelectedRow(), 0).toString();
			String user = jTableUser.getValueAt(jTableUser.getSelectedRow(), 0)
					.toString();
			getSpecificDataOpeUser(operator, code, user);
		}
		if (jTableOperator.getSelectedRowCount() > 0
				&& jTableUser.getSelectedRowCount() == 0
				&& jTableDevice.getSelectedRowCount() > 0) {
			String code = jTableOperator.getValueAt(
					jTableOperator.getSelectedRow(), 0).toString();
			String operator = jTableOperator.getValueAt(
					jTableOperator.getSelectedRow(), 1).toString();
			String vendor = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 1).toString();
			String model = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 2).toString();
			String osVersion = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 3).toString();
			getSpecificDataOpeDev(code, operator, vendor, model, osVersion);
		}
		if (jTableOperator.getSelectedRowCount() == 0
				&& jTableUser.getSelectedRowCount() > 0
				&& jTableDevice.getSelectedRowCount() > 0) {
			String user = jTableUser.getValueAt(jTableUser.getSelectedRow(), 0)
					.toString();
			String vendor = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 1).toString();
			String model = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 2).toString();
			String osVersion = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 3).toString();
			getSpecificDataUseDev(user, vendor, model, osVersion);
		}
		if (jTableOperator.getSelectedRowCount() > 0
				&& jTableUser.getSelectedRowCount() > 0
				&& jTableDevice.getSelectedRowCount() > 0) {
			String operator = jTableOperator.getValueAt(
					jTableOperator.getSelectedRow(), 1).toString();
			String code = jTableOperator.getValueAt(
					jTableOperator.getSelectedRow(), 0).toString();
			String user = jTableUser.getValueAt(jTableUser.getSelectedRow(), 0)
					.toString();
			String vendor = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 1).toString();
			String model = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 2).toString();
			String osVersion = jTableDevice.getValueAt(
					jTableDevice.getSelectedRow(), 3).toString();
			getSpecificData(operator, code, user, vendor, model, osVersion);
		}
		if (jTableOperator.getSelectedRowCount() == 0
				&& jTableUser.getSelectedRowCount() == 0
				&& jTableDevice.getSelectedRowCount() == 0) {
			JOptionPane
					.showMessageDialog(jPanel4, "Wat are you trying to do ?");
		}

	}// GEN-LAST:event_jButtonGetResultActionPerformed

	private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField10ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField10ActionPerformed

	private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField9ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField9ActionPerformed

	private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField8ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField8ActionPerformed

	private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField6ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField6ActionPerformed

	private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField5ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField5ActionPerformed

	private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField4ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField4ActionPerformed

	private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField2ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField2ActionPerformed

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField1ActionPerformed

	private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField3ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField3ActionPerformed

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
		// TODO add your handling code here:
		exportToxls();
	}// GEN-LAST:event_jMenuItem1ActionPerformed

	private void jRadioButtononlineActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtononlineActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jRadioButtononlineActionPerformed

	private void jRadioButtonOfflineActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonOfflineActionPerformed
		// TODO add your handling code here:

	}// GEN-LAST:event_jRadioButtonOfflineActionPerformed

	private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jSearchButtonActionPerformed
		highlight(jRawDataPane, jSearchText.getText());
	}// GEN-LAST:event_jSearchButtonActionPerformed

	public void getPerformanceSummary() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		// dataSet.setValue(Double.valueOf(jTextField7.getText()),"Upload Rate"
		// ,"Min Upload Average" );
		dataSet.setValue(Double.valueOf(jTextField6.getText()), "Mobile",
				"Max Upload Average");
		// dataSet.setValue(Double.valueOf(jTextField12.getText()),"Mobile"
		// ,"Min Download Average" );
		dataSet.setValue(Double.valueOf(jTextField13.getText()), "Mobile",
				"Max Download Average");
		// dataSet.setValue(Double.valueOf(jTextField8.getText()),"Mobile"
		// ,"Max Latency" );
		dataSet.setValue(Double.valueOf(jTextField19.getText()), "WLAN",
				"Max Upload Average");
		dataSet.setValue(Double.valueOf(jTextField26.getText()), "WLAN",
				"Max Download Average");
		// dataSet.setValue(Double.valueOf(jTextField21.getText()),"WLAN"
		// ,"Max Latency" );
		JFreeChart chart = ChartFactory.createBarChart("Performance Summary",
				"Measurements", "Data Rate (kbits/s)", dataSet,
				PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.BLACK);
		ChartFrame frame = new ChartFrame("Performance Chart", chart);
		frame.setVisible(true);
		frame.setSize(650, 450);
		/*
		 * DefaultCategoryDataset dataSetWlan = new DefaultCategoryDataset();
		 * dataSetWlan
		 * .setValue(Double.valueOf(jTextField20.getText()),"Upload Rate"
		 * ,"Min Upload Average" );
		 * dataSetWlan.setValue(Double.valueOf(jTextField19
		 * .getText()),"Upload Rate" ,"Max Upload Average" );
		 * dataSetWlan.setValue
		 * (Double.valueOf(jTextField25.getText()),"Download Rate"
		 * ,"Min Download Average" );
		 * dataSetWlan.setValue(Double.valueOf(jTextField26
		 * .getText()),"Download Rate" ,"Max Download Average" );
		 * 
		 * JFreeChart chartWlan =
		 * ChartFactory.createBarChart("Performance Summary",
		 * "Wlan Measurements", "Data Rate", dataSetWlan,
		 * PlotOrientation.VERTICAL, false, true, false); CategoryPlot plotWlan
		 * = chart.getCategoryPlot();
		 * plotWlan.setRangeGridlinePaint(Color.BLACK); ChartFrame frameWlan =
		 * new ChartFrame("Performance Chart", chartWlan);
		 * frameWlan.setVisible(true); frameWlan.setSize(450,350);
		 */
	}

	public void removeHighlight(JTextComponent textComponent) {

		Highlighter highlight = textComponent.getHighlighter();
		Highlighter.Highlight[] highlights = highlight.getHighlights();

		for (int i = 0; i < highlights.length; i++) {

			if (highlights[i].getPainter() instanceof MyHighlightPainter) {
				highlight.removeHighlight(highlights[i]);

			}
		}

	}

	public void highlight(JTextComponent textComponent, String pattern) {
		removeHighlight(textComponent);

		try {

			Highlighter highlight = textComponent.getHighlighter();
			Document doc = textComponent.getDocument();
			String text = doc.getText(0, doc.getLength());
			int pos = 0;

			while ((pos = text.toUpperCase()
					.indexOf(pattern.toUpperCase(), pos)) >= 0) {

				highlight.addHighlight(pos, pos + pattern.length(),
						MyHighlightPainter);
				jRawDataPane.moveCaretPosition(pos);
				pos += pattern.length();
			}
		} catch (Exception e) {
			System.out.println("Error in Search");
		}
	}

	public void exportToxls() {
		try {
			TableModel model = jTableNetwork.getModel();
			FileWriter excel = new FileWriter(
					"\\\\home.org.aalto.fi\\kumark2\\data\\Desktop\\test.txt");

			for (int i = 0; i < model.getColumnCount(); i++) {
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					if ((model.getValueAt(i, j) == null)) {
						excel.write("Null" + "\t");
					} else {
						excel.write(model.getValueAt(i, j).toString() + "\t");
					}
				}
				excel.write("\n");
			}

			excel.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public boolean checkType(Measurement measureObj) {
		boolean isCell = true;

		if (measureObj.getTcpDownloadAverage().getNetwork().getType() != null
				&& measureObj.getTcpDownloadAverage().getNetwork().getType()
						.toString().equals("cell")) {
			isCell = true;
		} else if (measureObj.getTcpDownloadAverage().getNetwork().getType() != null
				&& measureObj.getTcpDownloadAverage().getNetwork().getType()
						.toString().equals("wlan")) {
			isCell = false;
		}

		return isCell;
	}

	public void getMapOnApp(String latlong) {

		int index = latlong.indexOf(",");
		String lat = latlong.substring(0, index - 1);
		String lon = latlong.substring(index + 1, latlong.length());

		double latitude = Double.valueOf(lat);
		double longitude = Double.valueOf(lon);
		System.out.println(latitude);
		System.out.println(longitude);

		final JXMapKit jXMapKit = new JXMapKit();
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		jXMapKit.setTileFactory(tileFactory);

		// location of Java
		final GeoPosition gp = new GeoPosition(latitude, longitude);

		String stats = getStatistics(latitude, longitude);
		int first = stats.indexOf('|');
		int second = stats.indexOf('|', first + 1);
		int third = stats.indexOf('|', second + 1);

		String count = stats.substring(0, first);
		String maxUp = stats.substring(first + 1, second);
		String maxDown = stats.substring(second + 1, third);
		String minLat = stats.substring(third + 1, stats.length());

		System.out.println(count);
		System.out.println(maxUp);
		System.out.println(maxDown);
		System.out.println(minLat);

		String display = "<html>Location Statistics :" + "<br>"
				+ "Number of Measurements: " + count + "<br>"
				+ "Maximum Upload: " + maxUp + "<br>" + "Maximum Download: "
				+ maxDown + "<br>" + "Minimum Latency: " + minLat;

		final JToolTip tooltip = new JToolTip();
		tooltip.setTipText(display);
		tooltip.setComponent(jXMapKit.getMainMap());
		jXMapKit.getMainMap().add(tooltip);

		jXMapKit.setZoom(4);
		jXMapKit.setAddressLocation(gp);

		jXMapKit.getMainMap().addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// ignore
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				JXMapViewer map = jXMapKit.getMainMap();

				// convert to world bitmap
				Point2D worldPos = map.getTileFactory().geoToPixel(gp,
						map.getZoom());

				// convert to screen
				Rectangle rect = map.getViewportBounds();
				int sx = (int) worldPos.getX() - rect.x;
				int sy = (int) worldPos.getY() - rect.y;
				Point screenPos = new Point(sx, sy);

				// check if near the mouse
				if (screenPos.distance(e.getPoint()) < 20) {
					screenPos.x -= tooltip.getWidth() / 2;

					tooltip.setLocation(screenPos);
					tooltip.setVisible(true);
				} else {
					tooltip.setVisible(false);
				}
			}
		});

		// Display the viewer in a JFrame
		JFrame frame = new JFrame("Measurement Location");
		frame.getContentPane().add(jXMapKit);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}

	public String getStatistics(double latitude, double longitude) {

		int count = 0;
		double latmax = latitude + 0.0001;
		double latmin = latitude - 0.0001;
		double longmax = longitude + 0.0001;
		double longmin = longitude - 0.0001;

		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {

			if (master.get(i).getLocations() != null) {
				for (int j = 0; j < master.get(i).getLocations().size(); j++) {
					if (master.get(i).getLocations().get(j).getLatitude() > latmin
							&& master.get(i).getLocations().get(j)
									.getLatitude() < latmax) {
						if (master.get(i).getLocations().get(j).getLongitude() > longmin
								&& master.get(i).getLocations().get(j)
										.getLongitude() < longmax) {

							// System.out.println(i);
							up.add(master.get(i).getTcpUploadAverage()
									.getValue());
							down.add(master.get(i).getTcpDownloadAverage()
									.getValue());
							lat.add(master.get(i).getPingAverage().getValue());
							count++;
							getRawData(i);
							continue;
						}
					}
				}
			}
		}
		double maxUp = getMax(up);
		// double minUp = getMin(up);
		double maxDown = getMax(down);
		// double minDown = getMin(down);
		// double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		// System.out.println(count+"|"+maxUp+"|"+maxDown+"|"+minLat);
		return count + "|" + maxUp + "|" + maxDown + "|" + minLat;

	}

	public void parseCSV(String fp) {

		String csvFileToRead = fp;
		jTextField4.setText(fp);
		jTextField17.setText(fp);
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		master = new ArrayList<Measurement>();
		masterWlan = new ArrayList<Measurement>();
		int count = 0;

		try {

			br = new BufferedReader(new FileReader(csvFileToRead));
			while ((line = br.readLine()) != null) {
				count++;
				if (count > 1) {

					// String [] fields = line.split(splitBy);

					// System.out.println(line);
					// split on comma(',')
					String[] measurements = line.split(splitBy);

					// create car object to store values
					Measurement measureObject = new Measurement();
					Client cliObj = new Client();
					User useObj = new User();
					Device devObj = new Device();
					Operator opeObj = new Operator();
					Subscriber subObj = new Subscriber();
					Network___ netObj = new Network___();
					PingAverage pingObj = new PingAverage();
					TcpDownloadAverage downLink = new TcpDownloadAverage();
					TcpUploadAverage upLink = new TcpUploadAverage();
					ArrayList<Location> locArray = new ArrayList<Location>();
					Location locObj = new Location();
					ArrayList<Network__> netArray = new ArrayList<Network__>();
					Network__ networkObj = new Network__();
					ArrayList<SignalStrength> sigArray = new ArrayList<SignalStrength>();
					SignalStrength sigObj = new SignalStrength();

					// add values from csv to car object
					measureObject.setStartedAt(measurements[0]);
					measureObject.setCreatedAt(measurements[0]);

					measureObject.setClient(cliObj);
					cliObj.setInstallationId(measurements[1]);

					measureObject.setUser(useObj);
					useObj.setUid(measurements[2]);

					measureObject.setDevice(devObj);
					devObj.setVendor(measurements[3]);
					devObj.setModel(measurements[4]);
					devObj.setOsVersion(measurements[5]);

					measureObject.setOperator(opeObj);
					opeObj.setSubscriber(subObj);
					subObj.setMcc(measurements[6]);
					subObj.setMnc(measurements[7]);
					subObj.setBrandName(measurements[8]);

					opeObj.setNetwork(netObj);
					netObj.setMcc(measurements[6]);
					netObj.setMnc(measurements[7]);
					netObj.setBrandName(measurements[8]);

					measureObject.setPingAverage(pingObj);
					if (!measurements[9].equals("")) {
						pingObj.setValue(Double.valueOf(measurements[9]));
					} else {
						pingObj.setValue(0);
					}
					measureObject.setTcpDownloadAverage(downLink);
					if (!measurements[10].equals("")) {
						downLink.setValue(Double.valueOf(measurements[10]));
					}

					measureObject.setTcpUploadAverage(upLink);
					if (!measurements[11].equals("")
							&& !measurements[11].equals("-")) {
						upLink.setValue(Double.valueOf(measurements[11]));
					}

					if (!measurements[12].equals("-")) {
						locObj.setLatitude(Double.valueOf(measurements[12]));
					}

					if (!measurements[13].equals("-")) {
						locObj.setLongitude(Double.valueOf(measurements[13]));
					}

					locArray.add(locObj);
					measureObject.setLocations(locArray);

					if (!measurements[14].equals("-")) {
						networkObj.setCellId(measurements[14]);
					}

					if (!measurements[15].equals("-")) {
						networkObj.setAreaCode(measurements[15]);
					}

					if (!measurements[16].equals("-")) {
						networkObj
								.setTechnology(Long.valueOf(measurements[16]));
					}

					netArray.add(networkObj);
					measureObject.setNetwork(netArray);

					if (!measurements[17].equals("-")) {
						sigObj.setValue(Long.valueOf(measurements[17]));
					}

					sigArray.add(sigObj);
					measureObject.setSignalStrengths(sigArray);

					// adding car objects to a list
					master.add(measureObject);

				}
			}
			// print values stored in carList
			System.out.println(master.size());
			numOfMeasurements();
			numOfDevice();
			numOfOperator();
			numOfUsers();
			maxUploadAvg(master);
			minUploadAvg(master);
			minLatency(master);
			maxLatency(master);
			minDownloadAvg(master);
			maxDownloadAvg(master);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void parser(String fp) throws IOException {

		try {
			jTextField4.setText(fp);
			jTextField17.setText(fp);
			JsonReader reader = new JsonReader(new FileReader(fp));
			master = new ArrayList<Measurement>();
			masterWlan = new ArrayList<Measurement>();
			// BufferedReader br = new BufferedReader(new FileReader(fp));
			Gson gson = new Gson();
			jPanel3.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			jStatusBar.setText("Starting to read File");
			reader.beginArray();
			while (reader.hasNext()) {
				Measurement measureObj = gson.fromJson(reader,
						Measurement.class);
				measureObj.setFileName(fp);
				boolean type = checkType(measureObj);
				if (type == true) {
					jStatusBar.setText("Adding object to Mobile Array");
					master.add(measureObj);
				} else {
					jStatusBar.setText("Adding object to WLAN Array");
					masterWlan.add(measureObj);
				}

			}

			jStatusBar.setText("File parsing completed");
			jPanel3.setCursor(Cursor.getDefaultCursor());
			reader.endArray();
			System.out.println(master.size());
			reader.close();

			// displayText(master);
			jStatusBar.setText("Calling Table functions");
			numOfMeasurements();
			if (master.size() > 0) {
				numOfUsers();
				numOfOperator();
				numOfDevice();
				maxUploadAvg(master);
				minUploadAvg(master);
				minLatency(master);
				maxLatency(master);
				minDownloadAvg(master);
				maxDownloadAvg(master);
				// getNetworkData(master);
			}

			// getRawData();
			if (masterWlan.size() > 0) {
				numOfUsersWlan();
				numOfOperatorWlan();
				numOfDeviceWlan();
				maxUploadAvgWlan(masterWlan);
				minUploadAvgWlan(masterWlan);
				minLatencyWlan(masterWlan);
				maxLatencyWlan(masterWlan);
				minDownloadAvgWlan(masterWlan);
				maxDownloadAvgWlan(masterWlan);
				getNetworkDataWlan(masterWlan);
			}

			boolean flag = true;

			do {
				Thread back = new Thread(new Runnable() {
					public void run() {
						System.out.println("Opening new Thread");
						getRawDataPane();
					}
				});
				back.start();
			} while (!flag);
			jStatusBar.setText("Complete");
			// dateFormat();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void displayText(ArrayList<Measurement> object) {
	 * jTextField1.setText(object.get(0).getCreatedAt()); }
	 */

	public void parserChoice() {
		JPanel choicePanel = new JPanel();

		JRadioButton mobileMeasurement = new JRadioButton("Mobile Measurements");
		JRadioButton wlanMeasurement = new JRadioButton("WLAN Measurements");
		JRadioButton both = new JRadioButton("Both");

		ButtonGroup choices = new ButtonGroup();
		choices.add(both);
		choices.add(mobileMeasurement);
		choices.add(wlanMeasurement);

		choicePanel.add(mobileMeasurement);
		choicePanel.add(wlanMeasurement);
		choicePanel.add(both);

		JOptionPane.showOptionDialog(null, choicePanel, "Radio Test",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, null, null);

	}

	public void clearApp() {

		jStatusBar.setText("Clearing Application");
		if (master != null) {
			master.clear();
			masterWlan.clear();
		}
		tableModel.setRowCount(0);
		deviceModel.setRowCount(0);
		userModel.setRowCount(0);
		networkTableModel.setRowCount(0);
		locationTabelModel.setRowCount(0);
		signalStrengthTableModel.setRowCount(0);
		tableModelW.setRowCount(0);
		deviceModelW.setRowCount(0);
		userModelW.setRowCount(0);
		networkTableModelW.setRowCount(0);
		locationTabelModelW.setRowCount(0);
		signalStrengthTableModelW.setRowCount(0);

		jTextField1.setText("");
		jTextField2.setText("");
		jTextField3.setText("");
		jTextField4.setText("");
		jTextField5.setText("");
		jTextField6.setText("");
		jTextField7.setText("");
		jTextField8.setText("");
		jTextField9.setText("");
		jTextField10.setText("");
		jTextField11.setText("");
		jTextField12.setText("");
		jTextField13.setText("");
		jTextField14.setText("");
		jTextField15.setText("");
		jTextField16.setText("");
		jTextField17.setText("");
		jTextField18.setText("");
		jTextField19.setText("");
		jTextField20.setText("");
		jTextField21.setText("");
		jTextField22.setText("");
		jTextField23.setText("");
		jTextField24.setText("");
		jTextField25.setText("");
		jTextField26.setText("");

		jNetworkTextPane.setText("");
		jNetworkTextPaneW.setText("");
		jRawDataPane.setText(" ");
		jTextArea1.setText(" ");
		jPanel5.removeAll();
		jPanel5.revalidate();

	}

	public void numOfMeasurements() {
		jTextField1.setText(String.valueOf(master.size() + masterWlan.size()));
		jTextField15.setText(String.valueOf(master.size() + masterWlan.size()));
		jTextField10.setText(String.valueOf(master.size()));
		jTextField23.setText(String.valueOf(master.size()));
		jTextField11.setText(String.valueOf(masterWlan.size()));
		jTextField24.setText(String.valueOf(masterWlan.size()));

		DefaultPieDataset measureDataset = new DefaultPieDataset();
		measureDataset.setValue("Cell", master.size());
		System.out.println("Size of master is " + master.size());
		measureDataset.setValue("WLAN", masterWlan.size());
		JFreeChart chart = ChartFactory.createPieChart("Measurements",
				measureDataset, true, true, true);
		ChartPanel pie = new ChartPanel(chart);
		pie.setSize(234, 202);
		pie.setVisible(true);
		jPanel5.repaint();
		jPanel5.add(pie);
		jPanel5.setVisible(true);
		jPanel5.setSize(234, 202);

	}

	public void getAvgPerformance(String areaCode, String cellId) {
		jTextArea1.setText("");

		int count = 0;
		boolean present = false;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {
			int index = master.get(i).getNetwork().size() - 1;

			if (index >= 0) {

				if (master.get(i).getNetwork().get(index).getAreaCode() != null
						&& master.get(i).getNetwork().get(index).getCellId() != null) {

					if (master.get(i).getNetwork()
							.get(master.get(i).getNetwork().size() - 1)
							.getAreaCode().toString().equals(areaCode)
							&& master.get(i).getNetwork()
									.get(master.get(i).getNetwork().size() - 1)
									.getCellId().toString().equals(cellId)) {

						present = true;

					}
				}
			}

			if (present == true) {
				count++;
				up.add(master.get(i).getTcpUploadAverage().getValue());
				down.add(master.get(i).getTcpDownloadAverage().getValue());
				lat.add(master.get(i).getPingAverage().getValue());
				getRawData(i);
				present = false;
				continue;
			}
		}
		double avgUp = 0.0;
		if (!up.isEmpty()) {
			for (Double upAvg : up) {
				avgUp += upAvg;
			}
			avgUp = avgUp / up.size();
		}
		double avgDown = 0.0;
		if (!down.isEmpty()) {
			for (Double downAvg : down) {
				avgDown += downAvg;
			}
			avgDown = avgDown / down.size();
		}
		double avgLat = 0;
		if (!lat.isEmpty()) {
			for (Double latAvg : lat) {
				avgLat += latAvg;
			}
			avgLat = avgLat / lat.size();
		}

		jTextArea1.setText("Average Upload: " + avgUp + "\n"
				+ "Average Download: " + avgDown + "\n" + "Average Latency: "
				+ avgLat + "\n" + "Number of measurements/cell: " + count
				+ "\n" + jTextArea1.getText());

		up.clear();
		down.clear();
		lat.clear();

	}

	/*
	 * public void numOfUsers(ArrayList<Measurement> object) {
	 * 
	 * List<String> user = new ArrayList<>(); for (int i = 0; i < object.size();
	 * i++) { user.add(object.get(i).getUser().getUid()); } Set<String>
	 * uniqueUser = new HashSet<String>(user);
	 * 
	 * //jList1.setListData(uniqueUser.toArray()); for (int j = 0; j <
	 * uniqueUser.size(); j++) { String uniqueUse[] = uniqueUser.toArray(new
	 * String[j]); Object[] objs = {j + 1, uniqueUse[j]};
	 * 
	 * 
	 * userModel.addRow(objs);
	 * 
	 * }
	 * 
	 * jTextField2.setText(String.valueOf(uniqueUser.size()));
	 * 
	 * }
	 */

	/*
	 * public void numOfUsersWlan(ArrayList<Measurement> object) {
	 * 
	 * List<String> user = new ArrayList<>(); for (int i = 0; i < object.size();
	 * i++) { user.add(object.get(i).getUser().getUid()); } Set<String>
	 * uniqueUser = new HashSet<String>(user);
	 * 
	 * //jList1.setListData(uniqueUser.toArray()); for (int j = 0; j <
	 * uniqueUser.size(); j++) { String uniqueUse[] = uniqueUser.toArray(new
	 * String[j]); Object[] objs = {j + 1, uniqueUse[j]};
	 * 
	 * 
	 * userModelW.addRow(objs);
	 * 
	 * }
	 * 
	 * jTextField16.setText(String.valueOf(uniqueUser.size()));
	 * 
	 * }
	 */

	public void numOfUsers() {
		userModel.setRowCount(0);
		List<String> user = new ArrayList<String>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getClient().getInstallationId() != null)
				user.add(master.get(i).getClient().getInstallationId());
		}
		Set<String> uniqueUser = new HashSet<String>(user);
		String uid = null;
		// jList1.setListData(uniqueUser.toArray());
		for (String users : uniqueUser) {
			String uniqueUse = users;

			uid = getUid(uniqueUse);

			int num = getMeasurementsPerUser(uniqueUse, uid);
			Object[] objs = { uniqueUse, uid, num };

			userModel.addRow(objs);

		}

		jTextField2.setText(String.valueOf(uniqueUser.size()));

	}

	public String getUid(String uniqueUse) {
		String uid = null;
		for (int i = 0; i < master.size(); i++)
			if (master.get(i).getClient().getInstallationId().equals(uniqueUse)) {
				uid = master.get(i).getUser().getUid();

			}
		return uid;
	}

	public void numOfUsersWlan() {
        userModelW.setRowCount(0);
        List<String> user = new ArrayList<>();
        for (int i = 0; i < masterWlan.size(); i++) {
            if(masterWlan.get(i).getClient().getInstallationId()!= null)
                user.add(masterWlan.get(i).getClient().getInstallationId());
        }
        Set<String> uniqueUser = new HashSet<String>(user);
        String uid= null;
        //jList1.setListData(uniqueUser.toArray());
        for (String users : uniqueUser) {
            String uniqueUse= users;
            
            uid = getUidW(uniqueUse);
            
            int num = getMeasurementsPerUserW(uniqueUse,uid);
            Object[] objs = {uniqueUse, uid,num};

            userModelW.addRow(objs);
           
        }
       
       jTextField16.setText(String.valueOf(uniqueUser.size()));
        
    }

	public String getUidW(String uniqueUse) {
		String uid = null;
		for (int i = 0; i < masterWlan.size(); i++)
			if (masterWlan.get(i).getClient().getInstallationId()
					.equals(uniqueUse)) {
				uid = masterWlan.get(i).getUser().getUid();

			}
		return uid;
	}

	public void updateUserModel (String operator, String code){
        
         userModel.setRowCount(0);
         List<String> user = new ArrayList<>();
         for (int i = 0; i < master.size(); i++) {
             if (master.get(i).getOperator().getNetwork().getBrandName() != null && master.get(i).getOperator().getNetwork().getMcc() != null && 
                    master.get(i).getOperator().getNetwork().getMnc() != null) {
                if (master.get(i).getOperator().getNetwork().getBrandName().toString().equals(operator) && 
                        (master.get(i).getOperator().getNetwork().getMcc()+"-"+master.get(i).getOperator().getNetwork().getMnc()).toString().equals(code)){
            user.add(master.get(i).getClient().getInstallationId());
             }
            }
         }
             
        Set<String> uniqueUser = new HashSet<String>(user);
        String uid= null;
        //jList1.setListData(uniqueUser.toArray());
        for (String users : uniqueUser) {
            String uniqueUse= users;
            
            uid = getUid(uniqueUse);
            
            int num = getMeasurementsPerUser(uniqueUse,uid);
            Object[] objs = {uniqueUse, uid,num};
            

            userModel.addRow(objs);
           
        }
    }

	public void updateUserModelW (String ipAddress){
        
         userModelW.setRowCount(0);
         List<String> user = new ArrayList<>();
         for (int i = 0; i < masterWlan.size(); i++) {
             if(masterWlan.get(i).getClient().getPublicIpAddress()!= null){
             if(masterWlan.get(i).getClient().getPublicIpAddress().toString().equals(ipAddress)){
            user.add(masterWlan.get(i).getClient().getInstallationId());
          
             }
            }
         }
             
        Set<String> uniqueUser = new HashSet<String>(user);
        String uid= null;
        //jList1.setListData(uniqueUser.toArray());
        for (String users : uniqueUser) {
            String uniqueUse= users;
            
            uid = getUidW(uniqueUse);
            
            int num = getMeasurementsPerUserW(uniqueUse,uid);
            Object[] objs = {uniqueUse, uid,num};

            userModelW.addRow(objs);
           
        }
    }

	public void updateUserModel (String operator,String code ,String vendor, String model, String osVersion){
            
         userModel.setRowCount(0);
         List<String> user = new ArrayList<>();
         for (int i = 0; i < master.size(); i++) {
            if (master.get(i).getOperator().getNetwork().getBrandName() != null && master.get(i).getOperator().getNetwork().getMcc() != null && 
                    master.get(i).getOperator().getNetwork().getMnc() != null){
                
            if (master.get(i).getOperator().getNetwork().getBrandName().toString().equals(operator) && 
                        (master.get(i).getOperator().getNetwork().getMcc()+"-"+master.get(i).getOperator().getNetwork().getMnc()).toString().equals(code)){
                
                 if(master.get(i).getDevice().getModel().toString().equals(model) && master.get(i).getDevice().getVendor().toString().equals(vendor)
                         && master.get(i).getDevice().getOsVersion().toString().equals(osVersion)){
                    
            user.add(master.get(i).getClient().getInstallationId()  );
                 }
             }
            }
         }
             
          Set<String> uniqueUser = new HashSet<String>(user);
        String uid= null;
        //jList1.setListData(uniqueUser.toArray());
        for (String users : uniqueUser) {
            String uniqueUse= users;
            
            uid = getUid(uniqueUse);
            
            int num = getMeasurementsPerUser(uniqueUse,uid);
            Object[] objs = {uniqueUse, uid,num};
            

            userModel.addRow(objs);
           
        }
    }

	public void updateUserModelW (String ipAddress, String vendor, String model, String osVersion){
           
         userModelW.setRowCount(0);
         List<String> user = new ArrayList<>();
         for (int i = 0; i < masterWlan.size(); i++) {
             if(masterWlan.get(i).getOperator().getNetwork().getBrandName()!= null){
                
             if(masterWlan.get(i).getClient().getPublicIpAddress().toString().equals(ipAddress)){
                
                 if(masterWlan.get(i).getDevice().getModel().toString().equals(model) && masterWlan.get(i).getDevice().getVendor().toString().equals(vendor)
                         && masterWlan.get(i).getDevice().getOsVersion().toString().equals(osVersion)){
                     
            user.add(masterWlan.get(i).getUser().getUid());
                 }
             }
            }
         }
             
         Set<String> uniqueUser = new HashSet<String>(user);
        String uid= null;
        //jList1.setListData(uniqueUser.toArray());
        for (String users : uniqueUser) {
            String uniqueUse= users;
            
            uid = getUidW(uniqueUse);
            
            int num = getMeasurementsPerUserW(uniqueUse,uid);
            Object[] objs = {uniqueUse, uid,num};

            userModelW.addRow(objs);
           
        }
    }

	public void updateDeviceUserModel(String operator, String vendor, String model, String osVersion){
            
         userModel.setRowCount(0);
         List<String> user = new ArrayList<>();
         for (int i = 0; i < master.size(); i++){
              if (master.get(i).getOperator().getNetwork().getBrandName()!=null && master.get(i).getDevice().getModel()!= null && master.get(i).getDevice().getVendor()!=null && master.get(i).getDevice().getOsVersion() != null){
             if(master.get(i).getOperator().getNetwork().getBrandName().toString().equals(operator)){
                  if(master.get(i).getDevice().getModel().toString().equals(model) && master.get(i).getDevice().getVendor().toString().equals(vendor)
                         && master.get(i).getDevice().getOsVersion().toString().equals(osVersion)){
                            
                 
                 
                     
            user.add(master.get(i).getClient().getInstallationId());
                 }
             
            }
              }
         }
             
        Set<String> uniqueUser = new HashSet<String>(user);
        String uid= null;
        //jList1.setListData(uniqueUser.toArray());
        for (String users : uniqueUser) {
            String uniqueUse= users;
            
            uid = getUid(uniqueUse);
            
            int num = getMeasurementsPerUser(uniqueUse,uid);
            Object[] objs = {uniqueUse, uid,num};
            

            userModel.addRow(objs);
           
        }
    }

	public void updateDeviceUserModelW(String ipAddress, String vendor, String model, String osVersion){
            
         userModelW.setRowCount(0);
         List<String> user = new ArrayList<>();
         for (int i = 0; i < masterWlan.size(); i++) {
              if (masterWlan.get(i).getDevice().getModel()!= null && masterWlan.get(i).getDevice().getVendor()!=null && masterWlan.get(i).getDevice().getOsVersion() != null){
             if(masterWlan.get(i).getDevice().getModel().toString().equals(model) && masterWlan.get(i).getDevice().getVendor().toString().equals(vendor)
                         && masterWlan.get(i).getDevice().getOsVersion().toString().equals(osVersion)){
                            if(masterWlan.get(i).getClient().getPublicIpAddress().toString().equals(ipAddress)){
                 
                 
                     
            user.add(masterWlan.get(i).getClient().getInstallationId());
                 }
             
            }
              }
         }
             
        Set<String> uniqueUser = new HashSet<String>(user);
        String uid= null;
        //jList1.setListData(uniqueUser.toArray());
        for (String users : uniqueUser) {
            String uniqueUse= users;
            
            uid = getUidW(uniqueUse);
            
            int num = getMeasurementsPerUserW(uniqueUse,uid);
            Object[] objs = {uniqueUse, uid,num};

            userModelW.addRow(objs);
           
        }
    }

	public void updateDeviceModel(String operator, String code) {
		deviceModel.setRowCount(0);
		ArrayList<Device> dev = new ArrayList<Device>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getOperator().getNetwork().getBrandName() != null
					&& master.get(i).getOperator().getNetwork().getMcc() != null
					&& master.get(i).getOperator().getNetwork().getMnc() != null) {
				if (master.get(i).getOperator().getNetwork().getBrandName()
						.toString().equals(operator)
						&& (master.get(i).getOperator().getNetwork().getMcc()
								+ "-" + master.get(i).getOperator()
								.getNetwork().getMnc()).toString().equals(code)) {

					dev.add(master.get(i).getDevice());

				}
			}
		}

		Set<Device> unique = new HashSet<Device>(dev);
		Device[] arr = unique.toArray(new Device[unique.size()]);
		int index = 1;
		for (Device device : arr) {

			int num = getMeasurementPerDevice(device.getVendor(),
					device.getModel(), device.getOsVersion());
			Object[] objs = { index, device.getVendor(), device.getModel(),
					device.getOsVersion(), num };
			index++;
			deviceModel.addRow(objs);
		}

	}

	public void updateDeviceModelW(String ipAddress) {
		deviceModelW.setRowCount(0);
		ArrayList<Device> dev = new ArrayList<Device>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getPublicIpAddress() != null) {
				if (masterWlan.get(i).getClient().getPublicIpAddress()
						.toString().equals(ipAddress)) {

					dev.add(masterWlan.get(i).getDevice());

				}
			}
		}

		Set<Device> unique = new HashSet<Device>(dev);
		Device[] arr = unique.toArray(new Device[unique.size()]);
		int index = 1;
		for (Device device : arr) {

			int num = getMeasurementPerDeviceW(device.getVendor(),
					device.getModel(), device.getOsVersion());
			Object[] objs = { index, device.getVendor(), device.getModel(),
					device.getOsVersion(), num };
			index++;
			deviceModelW.addRow(objs);
		}

	}

	public void updateUserDeviceModel(String user) {

		deviceModel.setRowCount(0);
		ArrayList<Device> dev = new ArrayList<Device>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getClient().getInstallationId() != null) {
				if (master.get(i).getClient().getInstallationId().toString()
						.equals(user)) {

					dev.add(master.get(i).getDevice());

				}
			}
		}

		Set<Device> unique = new HashSet<Device>(dev);
		Device[] arr = unique.toArray(new Device[unique.size()]);
		int index = 1;
		for (Device device : arr) {
			int num = getMeasurementPerDevice(device.getVendor(),
					device.getModel(), device.getOsVersion());
			Object[] objs = { index, device.getVendor(), device.getModel(),
					device.getOsVersion(), num };
			index++;
			deviceModel.addRow(objs);
		}

	}

	public void updateUserDeviceModelW(String user) {

		deviceModelW.setRowCount(0);
		ArrayList<Device> dev = new ArrayList<Device>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getInstallationId() != null) {
				if (masterWlan.get(i).getClient().getInstallationId()
						.toString().equals(user)) {

					dev.add(masterWlan.get(i).getDevice());

				}
			}
		}

		Set<Device> unique = new HashSet<Device>(dev);
		Device[] arr = unique.toArray(new Device[unique.size()]);
		int index = 1;
		for (Device device : arr) {
			int num = getMeasurementPerDeviceW(device.getVendor(),
					device.getModel(), device.getOsVersion());
			Object[] objs = { index, device.getVendor(), device.getModel(),
					device.getOsVersion(), num };
			index++;
			deviceModelW.addRow(objs);
		}

	}

	public void updateUserOperatorModel(String user) {

		tableModel.setRowCount(0);
		List<Network___> operator = new ArrayList<Network___>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getClient().getInstallationId() != null) {
				if (master.get(i).getClient().getInstallationId().toString()
						.equals(user)) {

					operator.add(master.get(i).getOperator().getNetwork());

				}
			}

		}

		Set<Network___> uniqueOperator = new HashSet<Network___>(operator);
		Network___[] arr = uniqueOperator.toArray(new Network___[uniqueOperator
				.size()]);

		// DefaultPieDataset opeDataset = new DefaultPieDataset ();
		int mesPerOpe;
		for (Network___ ope : arr) {

			mesPerOpe = getNumOfOper(ope.getBrandName(), ope.getMcc() + "-"
					+ ope.getMnc());
			Object[] objs = { ope.getMcc() + "-" + ope.getMnc(),
					ope.getBrandName(), mesPerOpe };

			tableModel.addRow(objs);

		}

		// jTextField3.setText(String.valueOf(uniqueOperator.size()));

	}

	public void updateUserOperatorModelW(String user) {

		tableModelW.setRowCount(0);
		List<Client> client = new ArrayList<Client>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getInstallationId() != null) {
				if (masterWlan.get(i).getClient().getInstallationId()
						.toString().equals(user)) {

					client.add(masterWlan.get(i).getClient());

				}
			}

		}

		Set<Client> uniqueOperator = new HashSet<Client>(client);
		Client[] arr = uniqueOperator
				.toArray(new Client[uniqueOperator.size()]);

		int mesPerOpe;
		int first;
		int second;
		String operatorName;
		for (Client ope : arr) {
			first = ope.getPublicIpAddress().indexOf(".");
			second = ope.getPublicIpAddress().indexOf(".", first + 1);
			mesPerOpe = getNumOfOperW(ope.getPublicIpAddress());
			operatorName = getOperNameW(ope.getPublicIpAddress());
			Object[] objs = { ope.getPublicIpAddress(), operatorName,
					ope.getPublicIpAddress().substring(0, second), mesPerOpe };

			tableModelW.addRow(objs);

		}

		// jTextField3.setText(String.valueOf(uniqueOperator.size()));

	}

	public void updateDeviceOperatorModel(String vendor, String model,
			String osVersion) {

		tableModel.setRowCount(0);
		List<Network___> operator = new ArrayList<Network___>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getDevice().getModel() != null
					&& master.get(i).getDevice().getVendor() != null
					&& master.get(i).getDevice().getOsVersion() != null) {
				if (master.get(i).getDevice().getModel().toString()
						.equals(model)
						&& master.get(i).getDevice().getVendor().toString()
								.equals(vendor)
						&& master.get(i).getDevice().getOsVersion().toString()
								.equals(osVersion)) {

					operator.add(master.get(i).getOperator().getNetwork());

				}
			}

		}

		Set<Network___> uniqueOperator = new HashSet<Network___>(operator);
		Network___[] arr = uniqueOperator.toArray(new Network___[uniqueOperator
				.size()]);

		// DefaultPieDataset opeDataset = new DefaultPieDataset ();
		int mesPerOpe;
		for (Network___ ope : arr) {

			mesPerOpe = getNumOfOper(ope.getBrandName(), ope.getMcc() + "-"
					+ ope.getMnc());
			Object[] objs = { ope.getMcc() + "-" + ope.getMnc(),
					ope.getBrandName(), mesPerOpe };

			tableModel.addRow(objs);

		}

		// jTextField3.setText(String.valueOf(uniqueOperator.size()));

	}

	public void updateDeviceOperatorModelW(String vendor, String model,
			String osVersion) {

		tableModelW.setRowCount(0);
		List<Client> client = new ArrayList<Client>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getDevice().getModel() != null
					&& masterWlan.get(i).getDevice().getVendor() != null
					&& masterWlan.get(i).getDevice().getOsVersion() != null) {
				if (masterWlan.get(i).getDevice().getModel().toString()
						.equals(model)
						&& masterWlan.get(i).getDevice().getVendor().toString()
								.equals(vendor)
						&& masterWlan.get(i).getDevice().getOsVersion()
								.toString().equals(osVersion)) {

					client.add(masterWlan.get(i).getClient());

				}
			}

		}

		Set<Client> uniqueOperator = new HashSet<Client>(client);
		Client[] arr = uniqueOperator
				.toArray(new Client[uniqueOperator.size()]);

		int mesPerOpe;
		int first;
		int second;
		String operatorName;
		for (Client ope : arr) {
			first = ope.getPublicIpAddress().indexOf(".");
			second = ope.getPublicIpAddress().indexOf(".", first + 1);
			mesPerOpe = getNumOfOperW(ope.getPublicIpAddress());
			operatorName = getOperNameW(ope.getPublicIpAddress());
			Object[] objs = { ope.getPublicIpAddress(), operatorName,
					ope.getPublicIpAddress().substring(0, second), mesPerOpe };

			tableModelW.addRow(objs);

		}

		// jTextField3.setText(String.valueOf(uniqueOperator.size()));

	}

	public void updateLocationData(String installationId) {

		locationTabelModel.setRowCount(0);
		String areaCode = jTableCell.getValueAt(jTableCell.getSelectedRow(), 0)
				.toString();
		String cellId = jTableCell.getValueAt(jTableCell.getSelectedRow(), 1)
				.toString();
		ArrayList<Location> location = new ArrayList<Location>();
		for (int i = 0; i < master.size(); i++) {
			int index = master.get(i).getNetwork().size() - 1;
			if (master.get(i).getClient().getInstallationId() != null
					&& master.get(i).getClient().getInstallationId()
							.equals(installationId)) {
				if (index >= 0) {
					if (master.get(i).getNetwork().get(index).getAreaCode() != null
							&& master.get(i).getNetwork().get(index)
									.getCellId() != null) {
						if (master.get(i).getNetwork()
								.get(master.get(i).getNetwork().size() - 1)
								.getAreaCode().toString().equals(areaCode)
								&& master
										.get(i)
										.getNetwork()
										.get(master.get(i).getNetwork().size() - 1)
										.getCellId().toString().equals(cellId)) {
							for (int j = 0; j < master.get(i).getLocations()
									.size(); j++) {
								location.add(master.get(i).getLocations()
										.get(j));
							}
						}
					}
				}
			}

		}
		Location[] loc = location.toArray(new Location[location.size()]);

		for (Location l : loc) {
			String latlong = (String.valueOf(l.getLatitude()) + ", " + String
					.valueOf(l.getLongitude()));
			Object[] objs = { l.getSource(), latlong, l.getTime() };

			locationTabelModel.addRow(objs);
		}
		location.clear();

	}

	public void updateLocationDataW(String ticketId) {

		locationTabelModelW.setRowCount(0);
		ArrayList<Location> location = new ArrayList<Location>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getTicketId() != null
					&& masterWlan.get(i).getTicketId().equals(ticketId)) {
				for (int j = 0; j < masterWlan.get(i).getLocations().size(); j++) {
					location.add(masterWlan.get(i).getLocations().get(j));
				}

			}

		}
		Location[] loc = location.toArray(new Location[location.size()]);

		for (Location l : loc) {
			String latlong = (String.valueOf(l.getLatitude()) + ", " + String
					.valueOf(l.getLongitude()));
			Object[] objs = { l.getSource(), latlong, l.getTime() };

			locationTabelModelW.addRow(objs);
		}
		location.clear();

	}

	public void updateSignalStrength(String installationId) {

		signalStrengthTableModel.setRowCount(0);
		String areaCode = jTableCell.getValueAt(jTableCell.getSelectedRow(), 0)
				.toString();
		String cellId = jTableCell.getValueAt(jTableCell.getSelectedRow(), 1)
				.toString();
		ArrayList<SignalStrength> signalStrength = new ArrayList<SignalStrength>();
		for (int i = 0; i < master.size(); i++) {
			int index = master.get(i).getNetwork().size() - 1;
			if (master.get(i).getClient().getInstallationId() != null
					&& master.get(i).getClient().getInstallationId()
							.equals(installationId)) {
				if (index >= 0) {
					if (master.get(i).getNetwork().get(index).getAreaCode() != null
							&& master.get(i).getNetwork().get(index)
									.getCellId() != null) {
						if (master.get(i).getNetwork()
								.get(master.get(i).getNetwork().size() - 1)
								.getAreaCode().toString().equals(areaCode)
								&& master
										.get(i)
										.getNetwork()
										.get(master.get(i).getNetwork().size() - 1)
										.getCellId().toString().equals(cellId)) {
							for (int j = 0; j < master.get(i)
									.getSignalStrengths().size(); j++) {
								signalStrength.add(master.get(i)
										.getSignalStrengths().get(j));
							}

						}
					}
				}
			}

		}
		SignalStrength[] signal = signalStrength
				.toArray(new SignalStrength[signalStrength.size()]);

		for (SignalStrength sig : signal) {
			Object[] objs = { sig.getValue(), sig.getTime() };

			signalStrengthTableModel.addRow(objs);
		}

		signalStrength.clear();

	}

	public void updateSignalStrengthW(String ticketId) {

		signalStrengthTableModelW.setRowCount(0);
		ArrayList<SignalStrength> signalStrength = new ArrayList<SignalStrength>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getTicketId() != null
					&& masterWlan.get(i).getTicketId().equals(ticketId)) {
				for (int j = 0; j < masterWlan.get(i).getSignalStrengths()
						.size(); j++) {
					signalStrength.add(masterWlan.get(i).getSignalStrengths()
							.get(j));
				}

			}

		}
		SignalStrength[] signal = signalStrength
				.toArray(new SignalStrength[signalStrength.size()]);

		for (SignalStrength sig : signal) {
			Object[] objs = { sig.getValue(), sig.getTime() };

			signalStrengthTableModelW.addRow(objs);
		}

		signalStrength.clear();

	}

	public void moreInfo(String installationId) {
		jNetworkTextPane.setText(null);

		String areaCode = jTableCell.getValueAt(jTableCell.getSelectedRow(), 0)
				.toString();
		String cellId = jTableCell.getValueAt(jTableCell.getSelectedRow(), 1)
				.toString();
		StyledDocument doc = jNetworkTextPane.getStyledDocument();

		SimpleAttributeSet keyWord = new SimpleAttributeSet();
		StyleConstants.setForeground(keyWord, Color.RED);
		StyleConstants.setBackground(keyWord, Color.YELLOW);
		StyleConstants.setBold(keyWord, true);

		SimpleAttributeSet boldWord = new SimpleAttributeSet();
		StyleConstants.setBold(boldWord, true);
		StyleConstants.setUnderline(boldWord, true);

		String ServerIp = "";
		String ServerPort = "";
		String ClientPubIp = "";
		String ClientPubPort = "";
		String ClientLocIp = "";
		String ClientLocPort = "";
		String battery = "";
		String batteryStatus = "";
		for (int i = 0; i < master.size(); i++) {
			int index = master.get(i).getNetwork().size() - 1;
			if (master.get(i).getClient().getInstallationId() != null
					&& master.get(i).getClient().getInstallationId()
							.equals(installationId)) {
				if (index >= 0) {
					if (master.get(i).getNetwork().get(index).getAreaCode() != null
							&& master.get(i).getNetwork().get(index)
									.getCellId() != null) {
						if (master.get(i).getNetwork()
								.get(master.get(i).getNetwork().size() - 1)
								.getAreaCode().toString().equals(areaCode)
								&& master
										.get(i)
										.getNetwork()
										.get(master.get(i).getNetwork().size() - 1)
										.getCellId().toString().equals(cellId)) {
							if (master.get(i).getServer() != null
									&& master.get(i).getClient() != null) {
								ServerIp = master.get(i).getServer()
										.getIpAddress();
								ServerPort = String.valueOf(master.get(i)
										.getServer().getPort());
								ClientPubIp = master.get(i).getClient()
										.getPublicIpAddress();
								ClientPubPort = String.valueOf(master.get(i)
										.getClient().getPublicPort());
								ClientLocIp = master.get(i).getClient()
										.getLocalIpAddress();
								ClientLocPort = String.valueOf(master.get(i)
										.getClient().getLocalPort());
							}

							if (!master.get(i).getBattery().isEmpty()) {
								battery = String.valueOf(master.get(i)
										.getBattery().get(0).getLevel());
								if (master.get(i).getBattery().get(0)
										.isCharging() == true) {
									batteryStatus = "charging";
								} else {
									batteryStatus = "not charging";
								}

							}
						}
					}
				}
			}
		}
		try {
			doc.insertString(0, "Server: " + "\n", boldWord);
			doc.insertString(doc.getLength(), ServerIp + " : " + ServerPort
					+ "\n", keyWord);
			doc.insertString(doc.getLength(), " Client: " + "\n", boldWord);
			doc.insertString(doc.getLength(), ClientPubIp + " : "
					+ ClientPubPort + "    (Public)" + "\n", keyWord);
			doc.insertString(doc.getLength(), ClientLocIp + " : "
					+ ClientLocPort + "    (Local)" + "\n", keyWord);

			doc.insertString(doc.getLength(), "Battery: " + "\n", boldWord);
			doc.insertString(doc.getLength(), battery + " (" + batteryStatus
					+ " )" + "\n", keyWord);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void moreInfoW(String ticketId) {
		jNetworkTextPaneW.setText(null);
		StyledDocument doc = jNetworkTextPaneW.getStyledDocument();

		SimpleAttributeSet keyWord = new SimpleAttributeSet();
		StyleConstants.setForeground(keyWord, Color.RED);
		StyleConstants.setBackground(keyWord, Color.YELLOW);
		StyleConstants.setBold(keyWord, true);

		SimpleAttributeSet boldWord = new SimpleAttributeSet();
		StyleConstants.setBold(boldWord, true);
		StyleConstants.setUnderline(boldWord, true);

		String ServerIp = "";
		String ServerPort = "";
		String ClientPubIp = "";
		String ClientPubPort = "";
		String ClientLocIp = "";
		String ClientLocPort = "";
		String battery = "";
		String batteryStatus = "";
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getTicketId() != null
					&& masterWlan.get(i).getTicketId().equals(ticketId)) {
				ServerIp = masterWlan.get(i).getServer().getIpAddress();
				ServerPort = String.valueOf(masterWlan.get(i).getServer()
						.getPort());
				ClientPubIp = masterWlan.get(i).getClient()
						.getPublicIpAddress();
				ClientPubPort = String.valueOf(masterWlan.get(i).getClient()
						.getPublicPort());
				ClientLocIp = masterWlan.get(i).getClient().getLocalIpAddress();
				ClientLocPort = String.valueOf(masterWlan.get(i).getClient()
						.getLocalPort());
				battery = String.valueOf(masterWlan.get(i).getBattery().get(0)
						.getLevel());
				if (masterWlan.get(i).getBattery().get(0).isCharging() == true) {
					batteryStatus = "charging";
				} else {
					batteryStatus = "not charging";
				}

			}
		}
		try {
			doc.insertString(0, "Server: " + "\n", boldWord);
			doc.insertString(doc.getLength(), ServerIp + " : " + ServerPort
					+ "\n", keyWord);
			doc.insertString(doc.getLength(), " Client: " + "\n", boldWord);
			doc.insertString(doc.getLength(), ClientPubIp + " : "
					+ ClientPubPort + "    (Public)" + "\n", keyWord);
			doc.insertString(doc.getLength(), ClientLocIp + " : "
					+ ClientLocPort + "    (Local)" + "\n", keyWord);

			doc.insertString(doc.getLength(), "Battery: " + "\n", boldWord);
			doc.insertString(doc.getLength(), battery + " (" + batteryStatus
					+ " )" + "\n", keyWord);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void updateDeviceUserModel (String vendor, String model, String osVersion){
            
         userModel.setRowCount(0);
         List<String> user = new ArrayList<>();
         for (int i = 0; i < master.size(); i++) {
             if (master.get(i).getDevice().getModel()!= null && master.get(i).getDevice().getVendor()!=null && master.get(i).getDevice().getOsVersion() != null){
                 
                 if(master.get(i).getDevice().getModel().toString().equals(model) && master.get(i).getDevice().getVendor().toString().equals(vendor)
                         && master.get(i).getDevice().getOsVersion().toString().equals(osVersion)){
                     
             user.add(master.get(i).getClient().getInstallationId());
                 }
             }
            
         }
             
         Set<String> uniqueUser = new HashSet<String>(user);
        String uid= null;
        //jList1.setListData(uniqueUser.toArray());
        for (String users : uniqueUser) {
            String uniqueUse= users;
            
            uid = getUid(uniqueUse);
            
            int num = getMeasurementsPerUser(uniqueUse,uid);
            Object[] objs = {uniqueUse, uid,num};
            

            userModel.addRow(objs);
           
        }
    }

	public void updateDeviceUserModelW (String vendor, String model, String osVersion){
            
         userModelW.setRowCount(0);
         List<String> user = new ArrayList<>();
         for (int i = 0; i < masterWlan.size(); i++) {
             if (masterWlan.get(i).getDevice().getModel()!= null && masterWlan.get(i).getDevice().getVendor()!=null && masterWlan.get(i).getDevice().getOsVersion() != null){
                 
                 if(masterWlan.get(i).getDevice().getModel().toString().equals(model) && masterWlan.get(i).getDevice().getVendor().toString().equals(vendor)
                         && masterWlan.get(i).getDevice().getOsVersion().toString().equals(osVersion)){
                     
            user.add(masterWlan.get(i).getClient().getInstallationId());
                 }
             }
            
         }
             
         Set<String> uniqueUser = new HashSet<String>(user);
        String uid= null;
        //jList1.setListData(uniqueUser.toArray());
        for (String users : uniqueUser) {
            String uniqueUse= users;
            
            uid = getUidW(uniqueUse);
            
            int num = getMeasurementsPerUserW(uniqueUse,uid);
            Object[] objs = {uniqueUse, uid,num};

            userModelW.addRow(objs);
           
        }
    }

	public void updateUserOperatorModel(String user, String vendor,
			String model, String osVersion) {

		tableModel.setRowCount(0);
		List<Network___> operator = new ArrayList<Network___>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getClient().getInstallationId() != null) {
				if (master.get(i).getClient().getInstallationId().toString()
						.equals(user)) {
					if (master.get(i).getDevice().getModel().toString()
							.equals(model)
							&& master.get(i).getDevice().getVendor().toString()
									.equals(vendor)
							&& master.get(i).getDevice().getOsVersion()
									.toString().equals(osVersion)) {

						operator.add(master.get(i).getOperator().getNetwork());
					}
				}
			}

		}

		Set<Network___> uniqueOperator = new HashSet<Network___>(operator);
		Network___[] arr = uniqueOperator.toArray(new Network___[uniqueOperator
				.size()]);

		DefaultPieDataset opeDataset = new DefaultPieDataset();
		int mesPerOpe;
		for (Network___ ope : arr) {

			mesPerOpe = getNumOfOper(ope.getBrandName(), ope.getMcc() + "-"
					+ ope.getMnc());
			Object[] objs = { ope.getMcc() + "-" + ope.getMnc(),
					ope.getBrandName(), mesPerOpe };

			tableModel.addRow(objs);

		}

		// jTextField3.setText(String.valueOf(uniqueOperator.size()));

	}

	public void updateUserOperatorModelW(String user, String vendor,
			String model, String osVersion) {

		tableModelW.setRowCount(0);
		List<Client> client = new ArrayList<Client>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getInstallationId() != null) {
				if (masterWlan.get(i).getClient().getInstallationId()
						.toString().equals(user)) {
					if (masterWlan.get(i).getDevice().getModel().toString()
							.equals(model)
							&& masterWlan.get(i).getDevice().getVendor()
									.toString().equals(vendor)
							&& masterWlan.get(i).getDevice().getOsVersion()
									.toString().equals(osVersion)) {

						client.add(masterWlan.get(i).getClient());
					}
				}
			}

		}

		Set<Client> uniqueOperator = new HashSet<Client>(client);
		Client[] arr = uniqueOperator
				.toArray(new Client[uniqueOperator.size()]);

		int mesPerOpe;
		int first;
		int second;
		String operatorName;
		for (Client ope : arr) {
			first = ope.getPublicIpAddress().indexOf(".");
			second = ope.getPublicIpAddress().indexOf(".", first + 1);
			mesPerOpe = getNumOfOperW(ope.getPublicIpAddress());
			operatorName = getOperNameW(ope.getPublicIpAddress());
			Object[] objs = { ope.getPublicIpAddress(), operatorName,
					ope.getPublicIpAddress().substring(0, second), mesPerOpe };

			tableModelW.addRow(objs);

		}

		// jTextField3.setText(String.valueOf(uniqueOperator.size()));

	}

	public void updateDeviceOperatorModel(String user, String vendor,
			String model, String osVersion) {

		tableModel.setRowCount(0);
		List<Network___> operator = new ArrayList<Network___>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getDevice().getModel() != null
					&& master.get(i).getDevice().getVendor() != null
					&& master.get(i).getDevice().getOsVersion() != null) {
				if (master.get(i).getDevice().getModel().toString()
						.equals(model)
						&& master.get(i).getDevice().getVendor().toString()
								.equals(vendor)
						&& master.get(i).getDevice().getOsVersion().toString()
								.equals(osVersion)) {
					if (master.get(i).getClient().getInstallationId()
							.toString().equals(user)) {

						operator.add(master.get(i).getOperator().getNetwork());

					}
				}
			}
		}

		Set<Network___> uniqueOperator = new HashSet<Network___>(operator);
		Network___[] arr = uniqueOperator.toArray(new Network___[uniqueOperator
				.size()]);

		DefaultPieDataset opeDataset = new DefaultPieDataset();
		int mesPerOpe;
		for (Network___ ope : arr) {

			mesPerOpe = getNumOfOper(ope.getBrandName(), ope.getMcc() + "-"
					+ ope.getMnc());
			Object[] objs = { ope.getMcc() + "-" + ope.getMnc(),
					ope.getBrandName(), mesPerOpe };

			tableModel.addRow(objs);

		}

		// jTextField3.setText(String.valueOf(uniqueOperator.size()));

	}

	public void updateDeviceOperatorModelW(String user, String vendor,
			String model, String osVersion) {

		tableModelW.setRowCount(0);
		List<Client> client = new ArrayList<Client>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getDevice().getModel() != null
					&& masterWlan.get(i).getDevice().getVendor() != null
					&& masterWlan.get(i).getDevice().getOsVersion() != null) {
				if (masterWlan.get(i).getDevice().getModel().toString()
						.equals(model)
						&& masterWlan.get(i).getDevice().getVendor().toString()
								.equals(vendor)
						&& masterWlan.get(i).getDevice().getOsVersion()
								.toString().equals(osVersion)) {
					if (masterWlan.get(i).getClient().getInstallationId()
							.toString().equals(user)) {

						client.add(masterWlan.get(i).getClient());

					}
				}
			}
		}

		Set<Client> uniqueOperator = new HashSet<Client>(client);
		Client[] arr = uniqueOperator
				.toArray(new Client[uniqueOperator.size()]);

		int mesPerOpe;
		int first;
		int second;
		String operatorName;
		for (Client ope : arr) {
			first = ope.getPublicIpAddress().indexOf(".");
			second = ope.getPublicIpAddress().indexOf(".", first + 1);
			mesPerOpe = getNumOfOperW(ope.getPublicIpAddress());
			operatorName = getOperNameW(ope.getPublicIpAddress());
			Object[] objs = { ope.getPublicIpAddress(), operatorName,
					ope.getPublicIpAddress().substring(0, second), mesPerOpe };

			tableModelW.addRow(objs);

		}

		// jTextField3.setText(String.valueOf(uniqueOperator.size()));

	}

	public void updateDeviceModelUser(String operator, String code, String user) {
		deviceModel.setRowCount(0);
		ArrayList<Device> dev = new ArrayList<Device>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getOperator().getNetwork().getBrandName() != null
					&& master.get(i).getOperator().getNetwork().getMcc() != null
					&& master.get(i).getOperator().getNetwork().getMnc() != null) {
				if (master.get(i).getOperator().getNetwork().getBrandName()
						.toString().equals(operator)
						&& (master.get(i).getOperator().getNetwork().getMcc()
								+ "-" + master.get(i).getOperator()
								.getNetwork().getMnc()).toString().equals(code)) {
					if (master.get(i).getClient().getInstallationId() != null
							&& master.get(i).getClient().getInstallationId()
									.toString().equals(user)) {
						dev.add(master.get(i).getDevice());
					}
				}
			}
		}

		Set<Device> unique = new HashSet<Device>(dev);
		Device[] arr = unique.toArray(new Device[unique.size()]);
		int index = 1;
		for (Device device : arr) {
			int num = getMeasurementPerDevice(device.getVendor(),
					device.getModel(), device.getOsVersion());
			Object[] objs = { index, device.getVendor(), device.getModel(),
					device.getOsVersion(), num };
			index++;
			deviceModel.addRow(objs);
		}

	}

	public void updateDeviceModelW(String ipAddress, String user) {
		deviceModelW.setRowCount(0);
		ArrayList<Device> dev = new ArrayList<Device>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getPublicIpAddress() != null
					&& masterWlan.get(i).getClient().getPublicIpAddress()
							.toString().equals(ipAddress)) {
				if (masterWlan.get(i).getClient().getInstallationId() != null
						&& masterWlan.get(i).getClient().getInstallationId()
								.toString().equals(user)) {
					dev.add(masterWlan.get(i).getDevice());
				}
			}
		}

		Set<Device> unique = new HashSet<Device>(dev);
		Device[] arr = unique.toArray(new Device[unique.size()]);
		int index = 1;
		for (Device device : arr) {
			int num = getMeasurementPerDeviceW(device.getVendor(),
					device.getModel(), device.getOsVersion());
			Object[] objs = { index, device.getVendor(), device.getModel(),
					device.getOsVersion(), num };
			index++;
			deviceModelW.addRow(objs);
		}

	}

	/*
	 * public void numOfOperator(ArrayList<Measurement> object) {
	 * List<Network___> operator = new ArrayList<Network___>(); for (int i = 0;
	 * i < object.size(); i++) { if (master.get(i).getOperator().getNetwork()!=
	 * null) operator.add(object.get(i).getOperator().getNetwork()); }
	 * 
	 * 
	 * Set<Network___> uniqueOperator = new HashSet<Network___>(operator);
	 * Network___[] arr = uniqueOperator.toArray(new
	 * Network___[uniqueOperator.size()]);
	 * 
	 * DefaultPieDataset opeDataset = new DefaultPieDataset (); int mesPerOpe;
	 * for (Network___ ope : arr) {
	 * 
	 * mesPerOpe=getNumOfOper(ope.getBrandName()); Object[] objs =
	 * {ope.getMcc()+"-"+ope.getMnc(),ope.getBrandName(),mesPerOpe};
	 * 
	 * tableModel.addRow(objs);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * JFreeChart chart=
	 * ChartFactory.createPieChart("Operator",opeDataset,true,true,true);
	 * ChartPanel pie = new ChartPanel(chart); pie.setSize(1190,1000);
	 * pie.setVisible(true); jPanel12.repaint(); jPanel12.add(pie);
	 * //jPanel12.setVisible(true); jPanel12.setSize(1193,1006);
	 * jTextField3.setText(String.valueOf(uniqueOperator.size())); }
	 */

	public void numOfOperatorWlan(ArrayList<Measurement> object) {
        List<String> operator = new ArrayList<>();
        for (int i = 0; i < object.size(); i++) {
            operator.add(object.get(i).getOperator().getNetwork().getBrandName());
        }
        

        Set<String> uniqueOperator = new HashSet<String>(operator);
        

        int mesPerOpe;
        for (int j = 0; j < uniqueOperator.size(); j++) {
            String uniqueOpe[] = uniqueOperator.toArray(new String[j]);
            mesPerOpe=getNumOfOperW(uniqueOpe[j]);
            Object[] objs = {j + 1, uniqueOpe[j],mesPerOpe};
           
            tableModelW.addRow(objs);
           
        }
      
        jTextField14.setText(String.valueOf(uniqueOperator.size()));
    }

	public void numOfOperator() {
		tableModel.setRowCount(0);
		List<Network___> operator = new ArrayList<Network___>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getOperator().getNetwork() != null)
				operator.add(master.get(i).getOperator().getNetwork());
		}

		Set<Network___> uniqueOperator = new HashSet<Network___>(operator);
		Network___[] arr = uniqueOperator.toArray(new Network___[uniqueOperator
				.size()]);

		DefaultPieDataset opeDataset = new DefaultPieDataset();
		int mesPerOpe;
		for (Network___ ope : arr) {

			mesPerOpe = getNumOfOper(ope.getBrandName(), ope.getMcc() + "-"
					+ ope.getMnc());
			Object[] objs = { ope.getMcc() + "-" + ope.getMnc(),
					ope.getBrandName(), mesPerOpe };

			tableModel.addRow(objs);

		}

		jTextField3.setText(String.valueOf(uniqueOperator.size()));
	}

	public void numOfOperatorWlan() {
		tableModelW.setRowCount(0);
		List<Client> client = new ArrayList<Client>();

		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getPublicIpAddress() != null)
				client.add(masterWlan.get(i).getClient());

		}

		Set<Client> uniqueOperator = new HashSet<Client>(client);
		Client[] arr = uniqueOperator
				.toArray(new Client[uniqueOperator.size()]);

		int mesPerOpe;
		int first;
		int second;
		String operatorName;
		for (Client ope : arr) {
			first = ope.getPublicIpAddress().indexOf(".");
			second = ope.getPublicIpAddress().indexOf(".", first + 1);
			mesPerOpe = getNumOfOperW(ope.getPublicIpAddress());
			operatorName = getOperNameW(ope.getPublicIpAddress());
			Object[] objs = { ope.getPublicIpAddress(), operatorName,
					ope.getPublicIpAddress().substring(0, second), mesPerOpe };

			tableModelW.addRow(objs);

		}

		jTextField14.setText(String.valueOf(uniqueOperator.size()));
	}

	public String getCountryName(String mcc) {
		String country = "";
		if (mcc.equals(null)) {
			country = "xyz";
		} else if (mcc.equals("244")) {
			country = "Finland";
		} else if (mcc.equals("208")) {
			country = "France";
		} else if (mcc.isEmpty()) {
			country = "Not retrieved";
		} else {
			country = "Unknown";
		}

		return country;
	}

	public void getCellList() {
		cellIdModel.setRowCount(0);
		List<Network__> cellIds = new ArrayList<Network__>();
		for (int i = 0; i < master.size(); i++) {

			if (master.get(i).getOperator().getNetwork().getBrandName() != null
					&& master.get(i).getOperator().getNetwork().getMcc() != null
					&& master.get(i).getOperator().getNetwork().getMnc() != null) {

				if (master
						.get(i)
						.getOperator()
						.getNetwork()
						.getBrandName()
						.toString()
						.equals(jTableOperator.getValueAt(
								jTableOperator.getSelectedRow(), 1))
						&& (master.get(i).getOperator().getNetwork().getMcc()
								+ "-" + master.get(i).getOperator()
								.getNetwork().getMnc()).toString().equals(
								jTableOperator.getValueAt(
										jTableOperator.getSelectedRow(), 0))) {
					for (int j = 0; j < master.get(i).getNetwork().size(); j++) {

						if (master.get(i).getNetwork().get(j).getCellId() != null
								|| master.get(i).getNetwork().get(j)
										.getAreaCode() != null)
							cellIds.add(master.get(i).getNetwork().get(j));

					}
				}
			}
		}
		if (!cellIds.isEmpty()) {
			Set<Network__> uniqueCell = new HashSet<Network__>(cellIds);
			Network__[] arr = uniqueCell.toArray(new Network__[uniqueCell
					.size()]);

			// int mesPerOpe;
			String returnMethod;
			float avgDownload;
			int numOfMes;
			for (Network__ cell : arr) {

				returnMethod = getAvgDownloadCell(cell.getAreaCode(),
						cell.getCellId());

				avgDownload = Float.valueOf(StringUtils.substringBefore(
						returnMethod, "|"));
				numOfMes = Integer.valueOf(StringUtils.substringAfter(
						returnMethod, "|"));
				Object[] objs = { cell.getAreaCode(), cell.getCellId(),
						avgDownload, numOfMes };

				cellIdModel.addRow(objs);

			}
		}
		cellIds.clear();

	}

	public String getAvgDownloadCell(String areaCode, String cellId) {

		int count = 0;
		boolean present = false;

		ArrayList<Double> down = new ArrayList<Double>();
		// System.out.println("AC " + areaCode);
		// System.out.println("CID " + cellId);
		for (int i = 0; i < master.size(); i++) {
			int index = master.get(i).getNetwork().size() - 1;

			if (index >= 0) {

				if (master.get(i).getNetwork().get(index).getAreaCode() != null
						&& master.get(i).getNetwork().get(index).getCellId() != null) {

					if (master.get(i).getNetwork()
							.get(master.get(i).getNetwork().size() - 1)
							.getAreaCode().toString().equals(areaCode)
							&& master.get(i).getNetwork()
									.get(master.get(i).getNetwork().size() - 1)
									.getCellId().toString().equals(cellId)) {

						present = true;

					}
				}
			}

			if (present == true) {
				count++;

				down.add(master.get(i).getTcpDownloadAverage().getValue());

				present = false;
				continue;
			}
		}

		double avgDown = 0.0;
		if (!down.isEmpty()) {
			for (Double downAvg : down) {
				avgDown += downAvg;
			}
			avgDown = avgDown / down.size();
		}

		return avgDown + "|" + down.size();

	}

	public void getNetworkData(String areaCode, String cellId) {
		// ArrayList<String> cellId = new ArrayList<String>();
		networkTableModel.setRowCount(0);

		locationTabelModel.setRowCount(0);
		signalStrengthTableModel.setRowCount(0);
		jNetworkTextPane.setText("");
		for (int i = 0; i < master.size(); i++) {
			int index = master.get(i).getNetwork().size() - 1;
			if (index >= 0) {
				if (master.get(i).getNetwork().get(index).getAreaCode() != null
						&& master.get(i).getNetwork().get(index).getCellId() != null) {

					if (master.get(i).getNetwork()
							.get(master.get(i).getNetwork().size() - 1)
							.getAreaCode().toString().equals(areaCode)
							&& master.get(i).getNetwork()
									.get(master.get(i).getNetwork().size() - 1)
									.getCellId().toString().equals(cellId)) {

						String installationId = master.get(i).getClient()
								.getInstallationId();

						String userId = master.get(i).getUser().getUid();

						String operator = master.get(i).getOperator()
								.getNetwork().getBrandName();

						String country = "unknown";

						if (master.get(i).getOperator().getNetwork().getMcc() != null) {

							country = getCountryName(master.get(i)
									.getOperator().getNetwork().getMcc());
						}
						/*
						 * cellId=removeDuplicateCellId(master.get(i).getNetwork(
						 * )); String cellIds = ""; for(String s:cellId){
						 * cellIds += s + "  ";
						 * 
						 * }
						 */
						double tcpUploadAvg = master.get(i)
								.getTcpUploadAverage().getValue();
						double tcpDownloadAvg = master.get(i)
								.getTcpDownloadAverage().getValue();
						double latency = master.get(i).getPingAverage()
								.getValue();
						Object[] objs = { installationId, userId, country,
								operator, latency, tcpUploadAvg, tcpDownloadAvg };
						networkTableModel.addRow(objs);
					}
				}
			}

			// cellId.clear();
		}

		// System.out.println(network);
	}

	public void getNetworkDataWlan(ArrayList<Measurement> object) {
		ArrayList<String> cellId = new ArrayList<String>();
		for (int i = 0; i < object.size(); i++) {
			String ticketId = object.get(i).getTicketId();
			String userId = object.get(i).getUser().getUid();
			String operator = object.get(i).getOperator().getNetwork()
					.getBrandName();
			String country = "unknown";
			if (object.get(i).getOperator().getNetwork().getMcc() != null) {
				country = getCountryName(object.get(i).getOperator()
						.getNetwork().getMcc());
			}
			cellId = removeDuplicateCellId(object.get(i).getNetwork());
			String cellIds = "";
			for (String s : cellId) {
				cellIds += s + "  ";

			}
			double tcpUploadAvg = object.get(i).getTcpUploadAverage()
					.getValue();
			double tcpDownloadAvg = object.get(i).getTcpDownloadAverage()
					.getValue();
			Object[] objs = { i + 1, ticketId, userId, country, operator,
					cellIds, tcpUploadAvg, tcpDownloadAvg };
			networkTableModelW.addRow(objs);
			cellId.clear();

		}

		// System.out.println("Geting wlan network data");

		// System.out.println(network);
	}

	public ArrayList<String> removeDuplicateCellId (List<Network__> net){
         //Object[] objs;
         ArrayList<String> cellId = new ArrayList<>();
         for (int i = 0; i < net.size(); i++) {
            cellId.add(net.get(i).getCellId());
        }
         Set<String> uniqueNet= new HashSet<String>(cellId);
         cellId.clear();
         ArrayList<String> uniqueCellId = new ArrayList<>(uniqueNet);
         return uniqueCellId;
        
           
        }

	public void numOfDevice(ArrayList<Measurement> object) {
		ArrayList<Device> dev = new ArrayList<Device>();
		for (int i = 0; i < object.size(); i++) {
			dev.add(object.get(i).getDevice());
		}

		Set<Device> unique = new HashSet<Device>(dev);
		Device[] arr = unique.toArray(new Device[unique.size()]);
		int index = 1;
		for (Device device : arr) {

			Object[] objs = { index, device.getVendor(), device.getModel(),
					device.getOsVersion() };
			index++;
			deviceModel.addRow(objs);
		}
		jTextField5.setText(String.valueOf(unique.size()));
	}

	/*
	 * public void numOfDeviceWlan(ArrayList<Measurement> object) {
	 * ArrayList<Device> dev = new ArrayList<Device>(); for (int i = 0; i <
	 * object.size(); i++) { dev.add(object.get(i).getDevice()); }
	 * 
	 * Set<Device> unique = new HashSet<Device>(dev); Device[] arr =
	 * unique.toArray(new Device[unique.size()]); int index = 1; for (Device
	 * device : arr) {
	 * 
	 * Object[] objs = {index, device.getVendor(), device.getModel(),
	 * device.getOsVersion() }; index++; deviceModelW.addRow(objs); }
	 * jTextField18.setText(String.valueOf(unique.size())); }
	 */

	public void numOfDevice() {
		deviceModel.setRowCount(0);
		ArrayList<Device> dev = new ArrayList<Device>();
		for (int i = 0; i < master.size(); i++) {
			dev.add(master.get(i).getDevice());
		}

		Set<Device> unique = new HashSet<Device>(dev);
		Device[] arr = unique.toArray(new Device[unique.size()]);
		int index = 1;
		for (Device device : arr) {
			int num = getMeasurementPerDevice(device.getVendor(),
					device.getModel(), device.getOsVersion());
			Object[] objs = { index, device.getVendor(), device.getModel(),
					device.getOsVersion(), num };
			index++;
			deviceModel.addRow(objs);
		}
		jTextField5.setText(String.valueOf(unique.size()));
	}

	public int getMeasurementPerDevice(String a, String b, String c) {
		int count = 0;
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getDevice().getVendor() != null
					&& master.get(i).getDevice().getModel() != null
					&& master.get(i).getDevice().getOsVersion() != null) {
				if (master.get(i).getDevice().getVendor().toString().equals(a)
						&& master.get(i).getDevice().getModel().toString()
								.equals(b)
						&& master.get(i).getDevice().getOsVersion().toString()
								.equals(c)) {
					// System.out.println(i);
					count++;

				}
			}
		}
		return count;

	}

	public void numOfDeviceWlan() {
		deviceModelW.setRowCount(0);
		ArrayList<Device> dev = new ArrayList<Device>();
		for (int i = 0; i < masterWlan.size(); i++) {
			dev.add(masterWlan.get(i).getDevice());
		}

		Set<Device> unique = new HashSet<Device>(dev);
		Device[] arr = unique.toArray(new Device[unique.size()]);
		int index = 1;
		for (Device device : arr) {
			int num = getMeasurementPerDeviceW(device.getVendor(),
					device.getModel(), device.getOsVersion());
			Object[] objs = { index, device.getVendor(), device.getModel(),
					device.getOsVersion(), num };
			index++;
			deviceModelW.addRow(objs);
		}
		jTextField18.setText(String.valueOf(unique.size()));
	}

	public int getMeasurementPerDeviceW(String a, String b, String c) {
		int count = 0;
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getDevice().getVendor() != null
					&& masterWlan.get(i).getDevice().getModel() != null
					&& masterWlan.get(i).getDevice().getOsVersion() != null) {
				if (masterWlan.get(i).getDevice().getVendor().toString()
						.equals(a)
						&& masterWlan.get(i).getDevice().getModel().toString()
								.equals(b)
						&& masterWlan.get(i).getDevice().getOsVersion()
								.toString().equals(c)) {
					// System.out.println(i);
					count++;

				}
			}
		}
		return count;

	}

	public void maxUploadAvg(ArrayList<Measurement> object) {
		int size = object.size();
		for (int j = 0; j < size; j++) {
			double max = object.get(j).getTcpUploadAverage().getValue();
			DecimalFormat df = new DecimalFormat("0.00");
			for (int i = 0; i < object.size(); i++) {
				if (max < object.get(i).getTcpUploadAverage().getValue()) {
					max = object.get(i).getTcpUploadAverage().getValue();
				}
			}

			jTextField6.setText(String.valueOf(df.format(max)));
		}
	}

	public void maxUploadAvgWlan(ArrayList<Measurement> object) {
		double max = object.get(0).getTcpUploadAverage().getValue();
		for (int i = 0; i < object.size(); i++) {
			if (max < object.get(i).getTcpUploadAverage().getValue()) {
				max = object.get(i).getTcpUploadAverage().getValue();
			}
		}
		jTextField19.setText(String.valueOf(max));

	}

	public void minUploadAvg(ArrayList<Measurement> object) {
		int size = object.size();
		for (int j = 0; j < size; j++) {
			double min = object.get(0).getTcpUploadAverage().getValue();
			for (int i = 0; i < object.size(); i++) {
				if (min > object.get(i).getTcpUploadAverage().getValue()) {
					min = object.get(i).getTcpUploadAverage().getValue();
				}
			}
			jTextField7.setText(String.valueOf(min));
		}
	}

	public void minUploadAvgWlan(ArrayList<Measurement> object) {
		double min = object.get(0).getTcpUploadAverage().getValue();
		for (int i = 0; i < object.size(); i++) {
			if (min > object.get(i).getTcpUploadAverage().getValue()) {
				min = object.get(i).getTcpUploadAverage().getValue();
			}
		}
		jTextField20.setText(String.valueOf(min));
	}

	public void maxLatency(ArrayList<Measurement> object) {
		int size = object.size();
		for (int j = 0; j < size; j++) {
			double max = object.get(0).getPingAverage().getValue();
			for (int i = 0; i < object.size(); i++) {
				if (max < object.get(i).getPingAverage().getValue()) {
					max = object.get(i).getPingAverage().getValue();
				}
			}
			jTextField8.setText(String.valueOf(max));
		}
	}

	public void maxLatencyWlan(ArrayList<Measurement> object) {
		double max = object.get(0).getPingAverage().getValue();
		for (int i = 0; i < object.size(); i++) {
			if (max < object.get(i).getPingAverage().getValue()) {
				max = object.get(i).getPingAverage().getValue();
			}
		}
		jTextField21.setText(String.valueOf(max));
	}

	public void maxDownloadAvg(ArrayList<Measurement> object) {
		int size = object.size();
		for (int j = 0; j < size; j++) {
			double max = object.get(0).getTcpDownloadAverage().getValue();
			for (int i = 0; i < object.size(); i++) {
				if (max < object.get(i).getTcpDownloadAverage().getValue()) {
					max = object.get(i).getTcpDownloadAverage().getValue();
				}
			}
			jTextField13.setText(String.valueOf(max));
		}
	}

	public void maxDownloadAvgWlan(ArrayList<Measurement> object) {
		double max = object.get(0).getTcpDownloadAverage().getValue();
		for (int i = 0; i < object.size(); i++) {
			if (max < object.get(i).getTcpDownloadAverage().getValue()) {
				max = object.get(i).getTcpDownloadAverage().getValue();
			}
		}
		jTextField26.setText(String.valueOf(max));
	}

	public void minDownloadAvg(ArrayList<Measurement> object) {
		int size = object.size();
		for (int j = 0; j < size; j++) {
			double min = object.get(0).getTcpDownloadAverage().getValue();
			for (int i = 0; i < object.size(); i++) {
				if (min > object.get(i).getTcpDownloadAverage().getValue()) {
					min = object.get(i).getTcpDownloadAverage().getValue();
				}
			}
			jTextField12.setText(String.valueOf(min));
		}
	}

	public void minDownloadAvgWlan(ArrayList<Measurement> object) {
		double min = object.get(0).getTcpDownloadAverage().getValue();
		for (int i = 0; i < object.size(); i++) {
			if (min > object.get(i).getTcpDownloadAverage().getValue()) {
				min = object.get(i).getTcpDownloadAverage().getValue();
			}
		}
		jTextField25.setText(String.valueOf(min));
	}

	public void minLatency(ArrayList<Measurement> object) {
		int size = object.size();
		for (int j = 0; j < size; j++) {
			double min = object.get(0).getPingAverage().getValue();
			for (int i = 0; i < object.size(); i++) {
				if (min > object.get(i).getPingAverage().getValue()) {
					min = object.get(i).getPingAverage().getValue();
				}
			}
			jTextField9.setText(String.valueOf(min));
		}
	}

	public void minLatencyWlan(ArrayList<Measurement> object) {
		double min = object.get(0).getPingAverage().getValue();
		for (int i = 0; i < object.size(); i++) {
			if (min > object.get(i).getPingAverage().getValue()) {
				min = object.get(i).getPingAverage().getValue();
			}
		}
		jTextField22.setText(String.valueOf(min));
	}

	/*
	 * public void getRawData() { for (int i = 0; i < master.size(); i++) {
	 * jTextArea1.append("\n" + "<-----Measurement: " + (i + 1) + "----->");
	 * jTextArea1.append("\n" + "Download Average: " +
	 * master.get(i).getTcpDownloadAverage().getValue()); jTextArea1.append("\n"
	 * + "Upload Average: " + master.get(i).getTcpUploadAverage().getValue());
	 * jTextArea1.append("\n" + "Latency: " +
	 * master.get(i).getPingAverage().getValue()); jTextArea1.append("\n" +
	 * "<-----End of Measurement: " + (i + 1) + "----->" + "\n"); }
	 * 
	 * 
	 * }
	 */

	public void getRawData(int i) {
		jTextArea1.append("\n" + "<-----Measurement: " + (i + 1) + "----->");
		jTextArea1.append("\n" + "Created Time: "
				+ master.get(i).getCreatedAt());
		jTextArea1.append("\n" + "Download Average: "
				+ master.get(i).getTcpDownloadAverage().getValue());
		jTextArea1.append("\n" + "Upload Average: "
				+ master.get(i).getTcpUploadAverage().getValue());
		jTextArea1.append("\n" + "Latency: "
				+ master.get(i).getPingAverage().getValue());
		jTextArea1.append("\n" + "<-----End of Measurement: " + (i + 1)
				+ "----->" + "\n");

	}

	public void getRawDataW(int i) {
		jTextArea1.append("\n" + "<-----Measurement: " + (i + 1) + "----->");
		jTextArea1.append("\n" + "Created Time: "
				+ masterWlan.get(i).getCreatedAt());
		jTextArea1.append("\n" + "Download Average: "
				+ masterWlan.get(i).getTcpDownloadAverage().getValue());
		jTextArea1.append("\n" + "Upload Average: "
				+ masterWlan.get(i).getTcpUploadAverage().getValue());
		jTextArea1.append("\n" + "Latency: "
				+ masterWlan.get(i).getPingAverage().getValue());
		jTextArea1.append("\n" + "<-----End of Measurement: " + (i + 1)
				+ "----->" + "\n");

	}

	/*
	 * public void dateFormat(){ try { SimpleDateFormat simpleDateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
	 * simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); Date myDate =
	 * simpleDateFormat.parse(master.get(0).getCreatedAt());
	 * System.out.println(myDate); } catch (ParseException ex) {
	 * Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex); }
	 * }
	 */

	/*
	 * public void getUserData(String User) { for (int i = 0; i < master.size();
	 * i++) { if (master.get(i).getUser().getUid().toString().equals(User)) {
	 * //jTextArea1.setText(null);
	 * //jTextArea1.append(master.get(i).getUser().getUid());
	 * //jTextArea1.append("\n" + master.get(i).getUser().getProvider());
	 * getRawData(i);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * public void getUserDataW(String User) { for (int i = 0; i <
	 * masterWlan.size(); i++) { if
	 * (masterWlan.get(i).getUser().getUid().toString().equals(User)) {
	 * //jTextArea1.setText(null);
	 * //jTextArea1.append(master.get(i).getUser().getUid());
	 * //jTextArea1.append("\n" + master.get(i).getUser().getProvider());
	 * getRawData(i);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */

	public Double getMax(ArrayList<Double> maxVal) {

		double max = Collections.max(maxVal);
		return max;

	}

	public Double getMin(ArrayList<Double> minVal) {

		double min = Collections.min(minVal);
		return min;

	}

	/*
	 * public void getDeviceData(String vendor, String model, String osVersion)
	 * { int count = 0; ArrayList<Double> maxVal = new ArrayList<Double>(); for
	 * (int i = 0; i < master.size(); i++) { if (master.get(i).getDevice() !=
	 * null) { if
	 * ((master.get(i).getDevice().getVendor().toString().equals(vendor)) &&
	 * (master.get(i).getDevice().getModel().toString().equals(model)) &&
	 * (master.get(i).getDevice().getOsVersion().toString().equals(osVersion)))
	 * { //System.out.println(i); count++;
	 * maxVal.add(master.get(i).getTcpUploadAverage().getValue());
	 * getRawData(i); continue; } } } double max = getMax(maxVal);
	 * maxVal.clear();
	 * jTextArea1.setText("Number of Measurements for the Selection: " + count
	 * +"\n" +"Maximum Upload Average: "+ max +"\n" +jTextArea1.getText());
	 * 
	 * 
	 * }
	 * 
	 * public void getDeviceDataW(String vendor, String model, String osVersion)
	 * { int count = 0; for (int i = 0; i < masterWlan.size(); i++) { if
	 * (masterWlan.get(i).getDevice() != null) { if
	 * ((masterWlan.get(i).getDevice().getVendor().toString().equals(vendor)) &&
	 * (masterWlan.get(i).getDevice().getModel().toString().equals(model)) &&
	 * (masterWlan
	 * .get(i).getDevice().getOsVersion().toString().equals(osVersion))) {
	 * //System.out.println(i); count++; getRawData(i); continue; } } }
	 * jTextArea1.setText("Number of Measurements for the Selection: " + count
	 * +"\n" +jTextArea1.getText());
	 * 
	 * 
	 * }
	 */

	public void getSpecificData(String operator, String code, String user,
			String vendor, String model, String osVersion) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getOperator().getNetwork().getBrandName() != null
					&& master.get(i).getOperator().getNetwork().getMcc() != null
					&& master.get(i).getOperator().getNetwork().getMnc() != null
					&& master.get(i).getClient().getInstallationId() != null
					&& master.get(i).getDevice().getVendor() != null
					&& master.get(i).getDevice().getModel() != null
					&& master.get(i).getDevice().getOsVersion() != null) {
				if (master.get(i).getOperator().getNetwork().getBrandName()
						.toString().equals(operator)
						&& (master.get(i).getOperator().getNetwork().getMcc()
								+ "-" + master.get(i).getOperator()
								.getNetwork().getMnc()).toString().equals(code)
						&& master.get(i).getClient().getInstallationId()
								.toString().equals(user)
						&& master.get(i).getDevice().getVendor().toString()
								.equals(vendor)
						&& master.get(i).getDevice().getModel().toString()
								.equals(model)
						&& master.get(i).getDevice().getOsVersion().toString()
								.equals(osVersion)) {
					// System.out.println(i);
					count++;
					up.add(master.get(i).getTcpUploadAverage().getValue());
					down.add(master.get(i).getTcpDownloadAverage().getValue());
					lat.add(master.get(i).getPingAverage().getValue());
					getRawData(i);
					continue;
				}
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataW(String ipAddress, String user, String vendor,
			String model, String osVersion) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getPublicIpAddress() != null
					&& masterWlan.get(i).getClient().getInstallationId() != null
					&& masterWlan.get(i).getDevice().getVendor() != null
					&& masterWlan.get(i).getDevice().getModel() != null
					&& masterWlan.get(i).getDevice().getOsVersion() != null) {
				if (masterWlan.get(i).getClient().getPublicIpAddress()
						.toString().equals(ipAddress)
						&& masterWlan.get(i).getClient().getInstallationId()
								.toString().equals(user)
						&& masterWlan.get(i).getDevice().getVendor().toString()
								.equals(vendor)
						&& masterWlan.get(i).getDevice().getModel().toString()
								.equals(model)
						&& masterWlan.get(i).getDevice().getOsVersion()
								.toString().equals(osVersion)) {
					// System.out.println(i);
					count++;
					up.add(masterWlan.get(i).getTcpUploadAverage().getValue());
					down.add(masterWlan.get(i).getTcpDownloadAverage()
							.getValue());
					lat.add(masterWlan.get(i).getPingAverage().getValue());
					getRawDataW(i);
					continue;
				}
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataOperator(String operator, String code) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {

			if (master.get(i).getOperator().getNetwork().getBrandName() != null
					&& master.get(i).getOperator().getNetwork().getMcc() != null
					&& master.get(i).getOperator().getNetwork().getMnc() != null
					&& master.get(i).getOperator().getNetwork().getBrandName()
							.toString().equals(operator)
					&& (master.get(i).getOperator().getNetwork().getMcc() + "-" + master
							.get(i).getOperator().getNetwork().getMnc())
							.toString().equals(code)) {
				// System.out.println(i);
				up.add(master.get(i).getTcpUploadAverage().getValue());
				down.add(master.get(i).getTcpDownloadAverage().getValue());
				lat.add(master.get(i).getPingAverage().getValue());
				count++;
				getRawData(i);
				continue;
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataOperatorW(String ipAddress) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < masterWlan.size(); i++) {

			if (masterWlan.get(i).getClient().getPublicIpAddress() != null
					&& masterWlan.get(i).getClient().getPublicIpAddress()
							.toString().equals(ipAddress)) {
				// System.out.println(i);
				count++;
				up.add(masterWlan.get(i).getTcpUploadAverage().getValue());
				down.add(masterWlan.get(i).getTcpDownloadAverage().getValue());
				lat.add(masterWlan.get(i).getPingAverage().getValue());
				getRawDataW(i);
				continue;
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataUser(String user) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getClient().getInstallationId() != null) {
				if (master.get(i).getClient().getInstallationId().toString()
						.equals(user)) {
					// System.out.println(i);
					count++;
					up.add(master.get(i).getTcpUploadAverage().getValue());
					down.add(master.get(i).getTcpDownloadAverage().getValue());
					lat.add(master.get(i).getPingAverage().getValue());
					getRawData(i);
					continue;
				}
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataUserW(String user) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getInstallationId() != null) {
				if (masterWlan.get(i).getClient().getInstallationId()
						.toString().equals(user)) {
					// System.out.println(i);
					count++;
					up.add(masterWlan.get(i).getTcpUploadAverage().getValue());
					down.add(masterWlan.get(i).getTcpDownloadAverage()
							.getValue());
					lat.add(masterWlan.get(i).getPingAverage().getValue());
					getRawDataW(i);
					continue;
				}
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataDevice(String vendor, String model,
			String osVersion) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {

			if (master.get(i).getDevice().getVendor().toString().equals(vendor)
					&& master.get(i).getDevice().getModel().toString()
							.equals(model)
					&& master.get(i).getDevice().getOsVersion().toString()
							.equals(osVersion)) {
				// System.out.println(i);
				count++;
				up.add(master.get(i).getTcpUploadAverage().getValue());
				down.add(master.get(i).getTcpDownloadAverage().getValue());
				lat.add(master.get(i).getPingAverage().getValue());
				getRawData(i);
				continue;
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataDeviceW(String vendor, String model,
			String osVersion) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < masterWlan.size(); i++) {

			if (masterWlan.get(i).getDevice().getVendor().toString()
					.equals(vendor)
					&& masterWlan.get(i).getDevice().getModel().toString()
							.equals(model)
					&& masterWlan.get(i).getDevice().getOsVersion().toString()
							.equals(osVersion)) {
				// System.out.println(i);
				count++;
				up.add(masterWlan.get(i).getTcpUploadAverage().getValue());
				down.add(masterWlan.get(i).getTcpDownloadAverage().getValue());
				lat.add(masterWlan.get(i).getPingAverage().getValue());
				getRawDataW(i);
				continue;
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataOpeUser(String operator, String code, String user) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getOperator().getNetwork().getBrandName() != null
					&& master.get(i).getOperator().getNetwork().getMcc() != null
					&& master.get(i).getOperator().getNetwork().getMnc() != null
					&& master.get(i).getClient().getInstallationId() != null) {
				if (master.get(i).getOperator().getNetwork().getBrandName()
						.toString().equals(operator)
						&& (master.get(i).getOperator().getNetwork().getMcc()
								+ "-" + master.get(i).getOperator()
								.getNetwork().getMnc()).toString().equals(code)
						&& master.get(i).getClient().getInstallationId()
								.toString().equals(user)) {
					// System.out.println(i);
					count++;
					up.add(master.get(i).getTcpUploadAverage().getValue());
					down.add(master.get(i).getTcpDownloadAverage().getValue());
					lat.add(master.get(i).getPingAverage().getValue());
					getRawData(i);
					continue;
				}
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataOpeUserW(String ipAddress, String user) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getPublicIpAddress() != null
					&& masterWlan.get(i).getClient().getInstallationId() != null) {
				if (masterWlan.get(i).getClient().getPublicIpAddress()
						.toString().equals(ipAddress)
						&& masterWlan.get(i).getClient().getInstallationId()
								.toString().equals(user)) {
					// System.out.println(i);
					count++;
					up.add(masterWlan.get(i).getTcpUploadAverage().getValue());
					down.add(masterWlan.get(i).getTcpDownloadAverage()
							.getValue());
					lat.add(masterWlan.get(i).getPingAverage().getValue());
					getRawDataW(i);
					continue;
				}
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataOpeDev(String code, String operator,
			String vendor, String model, String osVersion) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getOperator().getNetwork().getBrandName() != null
					&& (master.get(i).getOperator().getNetwork().getMcc() + "-" + master
							.get(i).getOperator().getNetwork().getMnc()) != null
					&& master.get(i).getDevice().getVendor() != null
					&& master.get(i).getDevice().getModel() != null
					&& master.get(i).getDevice().getOsVersion() != null) {
				if (master.get(i).getOperator().getNetwork().getBrandName()
						.toString().equals(operator)
						&& (master.get(i).getOperator().getNetwork().getMcc()
								+ "-" + master.get(i).getOperator()
								.getNetwork().getMnc()).toString().equals(code)
						&& master.get(i).getDevice().getVendor().toString()
								.equals(vendor)
						&& master.get(i).getDevice().getModel().toString()
								.equals(model)
						&& master.get(i).getDevice().getOsVersion().toString()
								.equals(osVersion)) {
					// System.out.println(i);
					count++;
					up.add(master.get(i).getTcpUploadAverage().getValue());
					down.add(master.get(i).getTcpDownloadAverage().getValue());
					lat.add(master.get(i).getPingAverage().getValue());
					getRawData(i);
					continue;
				}
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataOpeDevW(String ipAddress, String vendor,
			String model, String osVersion) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getPublicIpAddress() != null
					&& masterWlan.get(i).getDevice().getVendor() != null
					&& masterWlan.get(i).getDevice().getModel() != null
					&& masterWlan.get(i).getDevice().getOsVersion() != null) {
				if (masterWlan.get(i).getClient().getPublicIpAddress()
						.toString().equals(ipAddress)
						&& masterWlan.get(i).getDevice().getVendor().toString()
								.equals(vendor)
						&& masterWlan.get(i).getDevice().getModel().toString()
								.equals(model)
						&& masterWlan.get(i).getDevice().getOsVersion()
								.toString().equals(osVersion)) {
					// System.out.println(i);
					count++;
					up.add(masterWlan.get(i).getTcpUploadAverage().getValue());
					down.add(masterWlan.get(i).getTcpDownloadAverage()
							.getValue());
					lat.add(masterWlan.get(i).getPingAverage().getValue());
					getRawDataW(i);
					continue;
				}
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataUseDev(String user, String vendor, String model,
			String osVersion) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getClient().getInstallationId() != null
					&& master.get(i).getDevice().getVendor() != null
					&& master.get(i).getDevice().getModel() != null
					&& master.get(i).getDevice().getOsVersion() != null) {
				if (master.get(i).getClient().getInstallationId().toString()
						.equals(user)
						&& master.get(i).getDevice().getVendor().toString()
								.equals(vendor)
						&& master.get(i).getDevice().getModel().toString()
								.equals(model)
						&& master.get(i).getDevice().getOsVersion().toString()
								.equals(osVersion)) {
					// System.out.println(i);
					count++;
					up.add(master.get(i).getTcpUploadAverage().getValue());
					down.add(master.get(i).getTcpDownloadAverage().getValue());
					lat.add(master.get(i).getPingAverage().getValue());
					getRawData(i);
					continue;
				}
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getSpecificDataUseDevW(String user, String vendor,
			String model, String osVersion) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < masterWlan.size(); i++) {

			if (masterWlan.get(i).getClient().getInstallationId().toString()
					.equals(user)
					&& masterWlan.get(i).getDevice().getVendor().toString()
							.equals(vendor)
					&& masterWlan.get(i).getDevice().getModel().toString()
							.equals(model)
					&& masterWlan.get(i).getDevice().getOsVersion().toString()
							.equals(osVersion)) {
				// System.out.println(i);
				count++;
				up.add(masterWlan.get(i).getTcpUploadAverage().getValue());
				down.add(masterWlan.get(i).getTcpDownloadAverage().getValue());
				lat.add(masterWlan.get(i).getPingAverage().getValue());
				getRawDataW(i);
				continue;
			}
		}
		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());

	}

	public void getOperatorData(String Ope) {
		int count = 0;
		ArrayList<Double> up = new ArrayList<Double>();
		ArrayList<Double> down = new ArrayList<Double>();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getOperator().getNetwork().getBrandName() != null) {
				if (master.get(i).getOperator().getNetwork().getBrandName()
						.toString().equals(Ope)) {
					count++;
					up.add(master.get(i).getTcpUploadAverage().getValue());
					down.add(master.get(i).getTcpDownloadAverage().getValue());
					lat.add(master.get(i).getPingAverage().getValue());
					getRawData(i);
				}
			}

		}

		double maxUp = getMax(up);
		double minUp = getMin(up);
		double maxDown = getMax(down);
		double minDown = getMin(down);
		double maxLat = getMax(lat);
		double minLat = getMin(lat);
		up.clear();
		down.clear();
		lat.clear();
		jTextArea1.setText("Number of Measurements for the Selection: " + count
				+ "\n" + "Maximum Upload Average: " + maxUp + "\n"
				+ "Maximum Upload Average: " + minUp + "\n"
				+ "Maximum Download Average: " + maxDown + "\n"
				+ "Maximum Download Average: " + minDown + "\n"
				+ "Maximum Latency: " + maxLat + "\n" + "Maximum Latency: "
				+ minLat + "\n" + jTextArea1.getText());
	}

	public int getNumOfOper(String Ope, String code) {
		int count = 0;
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getOperator().getNetwork().getBrandName() != null
					&& master.get(i).getOperator().getNetwork().getMcc() != null
					&& master.get(i).getOperator().getNetwork().getMnc() != null) {
				if (master.get(i).getOperator().getNetwork().getBrandName()
						.toString().equals(Ope)
						&& (master.get(i).getOperator().getNetwork().getMcc()
								+ "-" + master.get(i).getOperator()
								.getNetwork().getMnc()).toString().equals(code)) {
					count++;
					// getRawData(i);
				}
			}

		}

		// jTextArea1.append("Number of Measurements for the Operator: " +
		// count);
		return count;
	}

	public int getMeasurementsPerUser(String user, String uid) {
		int count = 0;
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getClient().getInstallationId() != null
					&& master.get(i).getUser().getUid() != null) {
				if (master.get(i).getClient().getInstallationId().toString()
						.equals(user)
						&& master.get(i).getUser().getUid().toString()
								.equals(uid)) {
					count++;
					// getRawData(i);
				}
			}

			if (master.get(i).getClient().getInstallationId() == null) {
				if (master.get(i).getUser().getUid().toString().equals(uid)) {
					count++;
					// getRawData(i);
				}
			}

			if (master.get(i).getUser().getUid() == null) {
				if (master.get(i).getClient().getInstallationId().equals(user)) {
					count++;
					// getRawData(i);
				}
			}

		}

		// jTextArea1.append("Number of Measurements for the Operator: " +
		// count);
		return count;
	}

	public int getMeasurementsPerUserW(String user, String uid) {
		int count = 0;
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getInstallationId() != null
					&& masterWlan.get(i).getUser().getUid() != null) {
				if (masterWlan.get(i).getClient().getInstallationId()
						.toString().equals(user)
						&& masterWlan.get(i).getUser().getUid().toString()
								.equals(uid)) {
					count++;
					// getRawData(i);
				}
			}

			if (masterWlan.get(i).getClient().getInstallationId() == null) {
				if (masterWlan.get(i).getUser().getUid().toString().equals(uid)) {
					count++;
					// getRawData(i);
				}
			}

			if (masterWlan.get(i).getUser().getUid() == null) {
				if (masterWlan.get(i).getClient().getInstallationId()
						.equals(user)) {
					count++;
					// getRawData(i);
				}
			}

		}

		// jTextArea1.append("Number of Measurements for the Operator: " +
		// count);
		return count;
	}

	public int getNumOfOperW(String Ope) {
		int count = 0;
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getPublicIpAddress() != null) {
				if (masterWlan.get(i).getClient().getPublicIpAddress()
						.toString().equals(Ope)) {
					count++;
					// getRawData(i);
				}
			}

		}

		// jTextArea1.append("Number of Measurements for the Operator: " +
		// count);
		return count;
	}

	public String getOperNameW(String Ope) {
		String ope = null;
		for (int i = 0; i < masterWlan.size(); i++) {
			if (masterWlan.get(i).getClient().getPublicIpAddress() != null) {
				if (masterWlan.get(i).getClient().getPublicIpAddress()
						.toString().equals(Ope)) {
					ope = masterWlan.get(i).getOperator().getNetwork()
							.getBrandName();
					// getRawData(i);
				}
			}

		}

		// jTextArea1.append("Number of Measurements for the Operator: " +
		// count);
		return ope;
	}

	public void getDeviceData(String Dev) {
		int count = 0;
		for (int i = 0; i < master.size(); i++) {
			if (master.get(i).getDevice() != null) // if(master.get(i).getOperator().getNetwork().getBrandName().toString().equals(Ope)){
			// count++;
			// getRawData(i);
			// }
			{
				jTextArea1.append("Number of Measurements for the Operator: "
						+ i);
			}

		}

		jTextArea1.append("Number of Measurements for the Operator: " + count);
	}

	public void getRawDataPane() {
		jRawDataPane.setText(null);
		StyledDocument doc = jRawDataPane.getStyledDocument();

		SimpleAttributeSet keyWord = new SimpleAttributeSet();
		StyleConstants.setBold(keyWord, true);

		SimpleAttributeSet boldWord = new SimpleAttributeSet();
		StyleConstants.setBold(boldWord, true);
		StyleConstants.setUnderline(boldWord, true);
		StyleConstants.setForeground(boldWord, Color.RED);
		StyleConstants.setBackground(boldWord, Color.YELLOW);

		SimpleAttributeSet heading = new SimpleAttributeSet();
		StyleConstants.setFontSize(heading, 16);
		StyleConstants.setUnderline(heading, true);
		StyleConstants.setBold(heading, true);

		SimpleAttributeSet measure = new SimpleAttributeSet();
		StyleConstants.setFontSize(measure, 12);
		StyleConstants.setItalic(measure, true);
		StyleConstants.setBold(measure, true);
		StyleConstants.setForeground(measure, Color.darkGray);

		try {
			doc.insertString(0, "Parsed Data" + "\n", heading);
			doc.insertString(doc.getLength(), "\n", null);

			doc.insertString(doc.getLength(), "Mobile Measurements" + "\n",
					boldWord);
			doc.insertString(doc.getLength(), "\n", null);

			for (int i = 0; i < master.size(); i++) {
				doc.insertString(doc.getLength(), "<---Measurement " + (i + 1)
						+ "--->" + "\n", measure);

				if (master.get(i).getCreatedAt() != null) {
					doc.insertString(doc.getLength(), "Created At: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getCreatedAt() + "\n", keyWord);
				}

				if (master.get(i).getStartedBy() != null) {
					doc.insertString(doc.getLength(), "Started By: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getStartedBy() + "\n", keyWord);
				}

				if (master.get(i).getStartedAt() != null) {
					doc.insertString(doc.getLength(), "Started At: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getStartedAt() + "\n", keyWord);
				}

				if (master.get(i).getTicketId() != null) {
					doc.insertString(doc.getLength(), "Ticket Id: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getTicketId() + "\n", keyWord);
				}

				if (master.get(i).getFinishedAt() != null) {
					doc.insertString(doc.getLength(), "Finished At: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getFinishedAt() + "\n", keyWord);
				}

				if (master.get(i).getTcpUploadAverage() != null) {
					doc.insertString(doc.getLength(), "Upload Average: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getTcpUploadAverage().getValue()
							+ "\n", keyWord);
				}

				if (master.get(i).getTcpDownloadAverage() != null) {
					doc.insertString(doc.getLength(), "Download Average: ",
							null);
					doc.insertString(doc.getLength(), master.get(i)
							.getTcpDownloadAverage().getValue()
							+ "\n", keyWord);
				}

				if (master.get(i).getSignalStrengths() != null) {
					doc.insertString(doc.getLength(), "Signal Strengths: ",
							null);
					for (int j = 0; j < master.get(i).getSignalStrengths()
							.size(); j++) {
						doc.insertString(doc.getLength(), master.get(i)
								.getSignalStrengths().get(j).getValue()
								+ "  ", keyWord);
					}
					doc.insertString(doc.getLength(), "\n", null);
				}

				if (master.get(i).getLocations() != null) {
					doc.insertString(doc.getLength(), "Location: " + "\n", null);
					for (int j = 0; j < master.get(i).getLocations().size(); j++) {
						doc.insertString(doc.getLength(),
								"    " + "Lat,Long: ", null);
						doc.insertString(doc.getLength(), "("
								+ master.get(i).getLocations().get(j)
										.getLatitude()
								+ ", "
								+ master.get(i).getLocations().get(0)
										.getLongitude() + ") ", keyWord);
						doc.insertString(doc.getLength(), "  " + "Source: ",
								null);
						doc.insertString(doc.getLength(), master.get(i)
								.getLocations().get(j).getSource(), keyWord);
						doc.insertString(doc.getLength(), "\n", null);
					}
				}
				if (master.get(i).getBattery() != null) {
					doc.insertString(doc.getLength(), "Battery: " + "\n", null);
					doc.insertString(doc.getLength(), "    " + "Level: ", null);
					if (!master.get(i).getBattery().isEmpty()) {
						doc.insertString(
								doc.getLength(),
								String.valueOf(master.get(i).getBattery()
										.get(0).getLevel())
										+ "\n", keyWord);
						doc.insertString(doc.getLength(), "    " + "Voltage: ",
								null);
						doc.insertString(
								doc.getLength(),
								String.valueOf(master.get(i).getBattery()
										.get(0).getVoltage())
										+ "\n", keyWord);
						// doc.insertString(doc.getLength(),"\n", null);
					}
				}

				if (master.get(i).getOperator().getNetwork() != null) {
					doc.insertString(doc.getLength(), "\n"
							+ "Network Operator: " + "\n", null);
					doc.insertString(doc.getLength(),
							"    " + "Company Name: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getOperator().getNetwork().getBrandName()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "MCC: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getOperator().getNetwork().getMcc()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "MNC: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getOperator().getNetwork().getMnc(), keyWord);
					doc.insertString(doc.getLength(), "\n", null);
				}

				if (master.get(i).getOperator().getSubscriber() != null) {
					doc.insertString(doc.getLength(), "Network Subscriber: "
							+ "\n", null);
					doc.insertString(doc.getLength(),
							"    " + "Company Name: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getOperator().getSubscriber().getBrandName()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "MCC: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getOperator().getSubscriber().getMcc()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "MNC: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getOperator().getSubscriber().getMnc()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "IMSI: ", null);
					doc.insertString(doc.getLength(), master.get(i)
							.getOperator().getSubscriber().getImsi(), keyWord);
					// doc.insertString(doc.getLength(),"\n", null);
				}

				if (master.get(i).getUser() != null) {
					doc.insertString(doc.getLength(), "User: " + "\n", null);
					doc.insertString(doc.getLength(), "    " + "ID: ", null);
					doc.insertString(doc.getLength(), master.get(i).getUser()
							.getUid()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "Provider: ",
							null);
					doc.insertString(doc.getLength(), master.get(i).getUser()
							.getProvider(), keyWord);

					doc.insertString(doc.getLength(), "\n", null);
				}

				if (master.get(i).getDevice() != null) {
					doc.insertString(doc.getLength(), "Device: " + "\n", null);
					doc.insertString(doc.getLength(), "    " + "Brand: ", null);
					doc.insertString(doc.getLength(), master.get(i).getDevice()
							.getVendor()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "Model: ", null);
					doc.insertString(doc.getLength(), master.get(i).getDevice()
							.getModel()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "OS Version: ",
							null);
					doc.insertString(doc.getLength(), master.get(i).getDevice()
							.getOsVersion()
							+ "\n", keyWord);
					// doc.insertString(doc.getLength(),"\n", null);
				}

				if (master.get(i).getServer() != null) {
					doc.insertString(doc.getLength(), "Server: " + "\n", null);
					doc.insertString(doc.getLength(), "    " + "IP Address: ",
							null);
					doc.insertString(doc.getLength(), master.get(i).getServer()
							.getIpAddress(), keyWord);
					doc.insertString(doc.getLength(), "  " + "Port: ", null);
					doc.insertString(doc.getLength(),
							String.valueOf(master.get(i).getServer().getPort())
									+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    "
							+ "Protocol Version: ", null);
					doc.insertString(
							doc.getLength(),
							String.valueOf(master.get(i).getServer()
									.getProtocolVersion())
									+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    "
							+ "Ticket requested at: ", null);
					doc.insertString(doc.getLength(), master.get(i).getServer()
							.getTicketRequestedAt()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    "
							+ "Ticket response at: ", null);
					doc.insertString(doc.getLength(), master.get(i).getServer()
							.getTicketResponseAt()
							+ "\n", keyWord);
					// doc.insertString(doc.getLength(),"\n", null);
				}

				if (master.get(i).getClient() != null) {
					doc.insertString(doc.getLength(), "Client: " + "\n", null);

					doc.insertString(doc.getLength(), "    " + "Local IP: ",
							null);
					doc.insertString(doc.getLength(), master.get(i).getClient()
							.getLocalIpAddress(), keyWord);
					doc.insertString(doc.getLength(), "  " + "Local Port: ",
							null);
					doc.insertString(
							doc.getLength(),
							String.valueOf(master.get(i).getClient()
									.getLocalPort())
									+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "Public IP: ",
							null);
					doc.insertString(doc.getLength(), master.get(i).getClient()
							.getPublicIpAddress(), keyWord);
					doc.insertString(doc.getLength(), "  " + "Public Port: ",
							null);
					doc.insertString(
							doc.getLength(),
							String.valueOf(master.get(i).getClient()
									.getPublicPort())
									+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    "
							+ "Installation Id: ", null);
					doc.insertString(doc.getLength(), master.get(i).getClient()
							.getInstallationId()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "SDK Version: ",
							null);
					if (!master.get(i).getClient().getSdk().getVersion()
							.equals(""))
						doc.insertString(doc.getLength(), master.get(i)
								.getClient().getSdk().getVersion()
								+ "\n", keyWord);
					else
						doc.insertString(doc.getLength(), "NA" + "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "Application: ",
							null);
					doc.insertString(doc.getLength(), master.get(i).getClient()
							.getApplication().getName()
							+ "-"
							+ master.get(i).getClient().getApplication()
									.getVersion(), keyWord);
					doc.insertString(doc.getLength(), "\n", null);
				}

				doc.insertString(doc.getLength(), "\n", null);
			}
			doc.insertString(doc.getLength(), "\n", null);
			doc.insertString(doc.getLength(), "WLAN Measurements" + "\n",
					boldWord);
			doc.insertString(doc.getLength(), "\n", null);

			for (int i = 0; i < masterWlan.size(); i++) {
				doc.insertString(doc.getLength(), "<---Measurement " + (i + 1)
						+ "--->" + "\n", measure);

				if (masterWlan.get(i).getCreatedAt() != null) {
					doc.insertString(doc.getLength(), "Created At: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getCreatedAt() + "\n", keyWord);
				}

				if (masterWlan.get(i).getStartedBy() != null) {
					doc.insertString(doc.getLength(), "Started By: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getStartedBy() + "\n", keyWord);
				}

				if (masterWlan.get(i).getStartedAt() != null) {
					doc.insertString(doc.getLength(), "Started At: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getStartedAt() + "\n", keyWord);
				}

				if (masterWlan.get(i).getTicketId() != null) {
					doc.insertString(doc.getLength(), "Ticket Id: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getTicketId() + "\n", keyWord);
				}

				if (masterWlan.get(i).getFinishedAt() != null) {
					doc.insertString(doc.getLength(), "Finished At: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getFinishedAt() + "\n", keyWord);
				}

				if (masterWlan.get(i).getTcpUploadAverage() != null) {
					doc.insertString(doc.getLength(), "Upload Average: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getTcpUploadAverage().getValue()
							+ "\n", keyWord);
				}

				if (masterWlan.get(i).getTcpDownloadAverage() != null) {
					doc.insertString(doc.getLength(), "Download Average: ",
							null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getTcpDownloadAverage().getValue()
							+ "\n", keyWord);
				}

				if (masterWlan.get(i).getSignalStrengths() != null) {
					doc.insertString(doc.getLength(), "Signal Strengths: ",
							null);
					for (int j = 0; j < masterWlan.get(i).getSignalStrengths()
							.size(); j++) {
						doc.insertString(doc.getLength(), masterWlan.get(i)
								.getSignalStrengths().get(j).getValue()
								+ "  ", keyWord);
					}
					doc.insertString(doc.getLength(), "\n", null);
				}

				if (masterWlan.get(i).getLocations() != null) {
					doc.insertString(doc.getLength(), "Location: " + "\n", null);
					for (int j = 0; j < masterWlan.get(i).getLocations().size(); j++) {
						doc.insertString(doc.getLength(),
								"    " + "Lat,Long: ", null);
						doc.insertString(
								doc.getLength(),
								"("
										+ masterWlan.get(i).getLocations()
												.get(j).getLatitude()
										+ ", "
										+ masterWlan.get(i).getLocations()
												.get(0).getLongitude() + ") ",
								keyWord);
						doc.insertString(doc.getLength(), "  " + "Source: ",
								null);
						doc.insertString(doc.getLength(), masterWlan.get(i)
								.getLocations().get(j).getSource(), keyWord);
						doc.insertString(doc.getLength(), "\n", null);
					}
				}
				if (masterWlan.get(i).getBattery() != null) {
					doc.insertString(doc.getLength(), "Battery: " + "\n", null);
					doc.insertString(doc.getLength(), "    " + "Level: ", null);
					doc.insertString(
							doc.getLength(),
							String.valueOf(masterWlan.get(i).getBattery()
									.get(0).getLevel())
									+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "Voltage: ",
							null);
					doc.insertString(
							doc.getLength(),
							String.valueOf(masterWlan.get(i).getBattery()
									.get(0).getVoltage())
									+ "\n", keyWord);
					// doc.insertString(doc.getLength(),"\n", null);
				}

				if (masterWlan.get(i).getOperator().getNetwork() != null) {
					doc.insertString(doc.getLength(), "Network Operator: "
							+ "\n", null);
					doc.insertString(doc.getLength(),
							"    " + "Company Name: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getOperator().getNetwork().getBrandName()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "MCC: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getOperator().getNetwork().getMcc()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "MNC: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getOperator().getNetwork().getMnc(), keyWord);
					doc.insertString(doc.getLength(), "\n", null);
				}

				if (masterWlan.get(i).getOperator().getSubscriber() != null) {
					doc.insertString(doc.getLength(), "Network Subscriber: "
							+ "\n", null);
					doc.insertString(doc.getLength(),
							"    " + "Company Name: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getOperator().getSubscriber().getBrandName()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "MCC: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getOperator().getSubscriber().getMcc()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "MNC: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getOperator().getSubscriber().getMnc()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "IMSI: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getOperator().getSubscriber().getImsi(), keyWord);
					// doc.insertString(doc.getLength(),"\n", null);
				}

				if (masterWlan.get(i).getUser() != null) {
					doc.insertString(doc.getLength(), "User: " + "\n", null);
					doc.insertString(doc.getLength(), "    " + "ID: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getUser().getUid()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "Provider: ",
							null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getUser().getProvider(), keyWord);

					doc.insertString(doc.getLength(), "\n", null);
				}

				if (masterWlan.get(i).getDevice() != null) {
					doc.insertString(doc.getLength(), "Device: " + "\n", null);
					doc.insertString(doc.getLength(), "    " + "Brand: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getDevice().getVendor()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "Model: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getDevice().getModel()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "OS Version: ",
							null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getDevice().getOsVersion()
							+ "\n", keyWord);
					// doc.insertString(doc.getLength(),"\n", null);
				}

				if (masterWlan.get(i).getServer() != null) {
					doc.insertString(doc.getLength(), "Server: " + "\n", null);
					doc.insertString(doc.getLength(), "    " + "IP Address: ",
							null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getServer().getIpAddress(), keyWord);
					doc.insertString(doc.getLength(), "  " + "Port: ", null);
					doc.insertString(
							doc.getLength(),
							String.valueOf(masterWlan.get(i).getServer()
									.getPort())
									+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    "
							+ "Protocol Version: ", null);
					doc.insertString(
							doc.getLength(),
							String.valueOf(masterWlan.get(i).getServer()
									.getProtocolVersion())
									+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    "
							+ "Ticket requested at: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getServer().getTicketRequestedAt()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    "
							+ "Ticket response at: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getServer().getTicketResponseAt()
							+ "\n", keyWord);
					// doc.insertString(doc.getLength(),"\n", null);
				}

				if (masterWlan.get(i).getClient() != null) {
					doc.insertString(doc.getLength(), "Client: " + "\n", null);

					doc.insertString(doc.getLength(), "    " + "Local IP: ",
							null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getClient().getLocalIpAddress(), keyWord);
					doc.insertString(doc.getLength(), "  " + "Local Port: ",
							null);
					doc.insertString(
							doc.getLength(),
							String.valueOf(masterWlan.get(i).getClient()
									.getLocalPort())
									+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "Public IP: ",
							null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getClient().getPublicIpAddress(), keyWord);
					doc.insertString(doc.getLength(), "  " + "Public Port: ",
							null);
					doc.insertString(
							doc.getLength(),
							String.valueOf(masterWlan.get(i).getClient()
									.getPublicPort())
									+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    "
							+ "Installation Id: ", null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getClient().getInstallationId()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "SDK Version: ",
							null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getClient().getSdk().getVersion()
							+ "\n", keyWord);
					doc.insertString(doc.getLength(), "    " + "Application: ",
							null);
					doc.insertString(doc.getLength(), masterWlan.get(i)
							.getClient().getApplication().getName()
							+ "-"
							+ masterWlan.get(i).getClient().getApplication()
									.getVersion(), keyWord);
					doc.insertString(doc.getLength(), "\n", null);
				}

				doc.insertString(doc.getLength(), "\n", null);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainForm main = new MainForm();
				main.setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JMenuItem contentsMenuItem;
	private javax.swing.JMenuItem copyMenuItem;
	private javax.swing.JMenuItem cutMenuItem;
	private javax.swing.JMenuItem deleteMenuItem;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButtonClearAll;
	private javax.swing.JButton jButtonClearAllWlan;
	private javax.swing.JButton jButtonGetResult;
	private javax.swing.JButton jButtonGetResultWlan;
	private javax.swing.JMenuItem jClearMenu;
	private javax.swing.JLabel jDeviceList;
	private javax.swing.JLabel jDeviceList1;
	private javax.swing.JList jFileList;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel23;
	private javax.swing.JLabel jLabel24;
	private javax.swing.JLabel jLabel25;
	private javax.swing.JLabel jLabel26;
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel28;
	private javax.swing.JLabel jLabel29;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel30;
	private javax.swing.JLabel jLabel31;
	private javax.swing.JLabel jLabel32;
	private javax.swing.JLabel jLabel33;
	private javax.swing.JLabel jLabel34;
	private javax.swing.JLabel jLabel35;
	private javax.swing.JLabel jLabel36;
	private javax.swing.JLabel jLabel37;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JTextPane jNetworkTextPane;
	private javax.swing.JTextPane jNetworkTextPaneW;
	private javax.swing.JLabel jOperatorList;
	private javax.swing.JLabel jOperatorList1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JRadioButton jRadioButtonOffline;
	private javax.swing.JRadioButton jRadioButtononline;
	private javax.swing.JTextPane jRawDataPane;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane10;
	private javax.swing.JScrollPane jScrollPane11;
	private javax.swing.JScrollPane jScrollPane12;
	private javax.swing.JScrollPane jScrollPane13;
	private javax.swing.JScrollPane jScrollPane14;
	private javax.swing.JScrollPane jScrollPane15;
	private javax.swing.JScrollPane jScrollPane16;
	private javax.swing.JScrollPane jScrollPane17;
	private javax.swing.JScrollPane jScrollPane18;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JScrollPane jScrollPane7;
	private javax.swing.JScrollPane jScrollPane8;
	private javax.swing.JScrollPane jScrollPane9;
	private javax.swing.JButton jSearchButton;
	private javax.swing.JTextField jSearchText;
	private javax.swing.JLabel jStatusBar;
	private javax.swing.JTable jTableCell;
	private javax.swing.JTable jTableDevice;
	private javax.swing.JTable jTableDeviceW;
	private javax.swing.JTable jTableLocation;
	private javax.swing.JTable jTableLocationW;
	private javax.swing.JTable jTableNetwork;
	private javax.swing.JTable jTableNetworkW;
	private javax.swing.JTable jTableOperator;
	private javax.swing.JTable jTableOperatorW;
	private javax.swing.JTable jTableSignalStrength;
	private javax.swing.JTable jTableSignalStrengthW;
	private javax.swing.JTable jTableUser;
	private javax.swing.JTable jTableUserW;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField10;
	private javax.swing.JTextField jTextField11;
	private javax.swing.JTextField jTextField12;
	private javax.swing.JTextField jTextField13;
	private javax.swing.JTextField jTextField14;
	private javax.swing.JTextField jTextField15;
	private javax.swing.JTextField jTextField16;
	private javax.swing.JTextField jTextField17;
	private javax.swing.JTextField jTextField18;
	private javax.swing.JTextField jTextField19;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField20;
	private javax.swing.JTextField jTextField21;
	private javax.swing.JTextField jTextField22;
	private javax.swing.JTextField jTextField23;
	private javax.swing.JTextField jTextField24;
	private javax.swing.JTextField jTextField25;
	private javax.swing.JTextField jTextField26;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JTextField jTextField6;
	private javax.swing.JTextField jTextField7;
	private javax.swing.JTextField jTextField8;
	private javax.swing.JTextField jTextField9;
	private javax.swing.JPanel jTextPanel;
	private javax.swing.JLabel jUserList;
	private javax.swing.JLabel jUserList1;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem openMenuItem;
	private javax.swing.JMenuItem pasteMenuItem;
	private javax.swing.JMenuItem saveAsMenuItem;
	private javax.swing.JMenuItem saveMenuItem;
	// End of variables declaration//GEN-END:variables
}
