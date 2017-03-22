package slim;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import delegate.ContactServiceDelegate;
import entities.Contact;
import entities.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class contactController implements Initializable {

	private ContactServiceDelegate delegate = new ContactServiceDelegate();

	private List<Contact> contactList = new ArrayList<Contact>();
	private List<Contact> receivedRequestsList = new ArrayList<Contact>();
	private List<Contact> sentRequestsList = new ArrayList<Contact>();
	
	public static final int idUserLoggedIn = 5;

	@FXML
	private TilePane cPane;

	@FXML
	private TilePane reqPane;

	@FXML
	private TilePane penPane;

	@FXML
	private TilePane sPane;

	@FXML
	private TreeView<String> treeView;

	@FXML
	private AnchorPane requestPane;

	@FXML
	private AnchorPane networkPane;

	@FXML
	private AnchorPane pendingPane;

	@FXML
	private AnchorPane searchPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		hidePanes();

		fillTreeView();

		cPane.setPadding(new Insets(5, 5, 5, 5));
		cPane.setVgap(4);
		cPane.setHgap(4);

		try {
			contactList = delegate.getContactsByUserId(idUserLoggedIn);
			for (Contact c : contactList) {

				TitledPane tp = new TitledPane();
				tp.setMaxSize(140, 140);
				tp.setMinSize(140, 140);

				User u1 = new User();
				if (c.getIdContact().getIdContactPK() == idUserLoggedIn)
					u1 = delegate.findUserById(c.getIdContact().getIdUserPK());
				else
					u1 = delegate.findUserById(c.getIdContact().getIdContactPK());
				String name = new String(u1.getFirstName() + " " + u1.getLastName());
				tp.setText(name);

				ImageView imageView = new ImageView(new Image("/slim/images/" + u1.getPicture()));
				imageView.setFitHeight(50);
				imageView.setPreserveRatio(true);
				// imageView.setFitWidth(50);
				imageView.setClip(new Circle(25, 25, 25));

				ImageView deleteIcon = new ImageView(new Image("/slim/images/delete-icon.png"));
				deleteIcon.setFitHeight(10);
				deleteIcon.setFitWidth(10);

				JFXButton btn = new JFXButton("Unfriend", deleteIcon);

				VBox vb = new VBox();
				vb.setAlignment(Pos.CENTER);
				vb.setPadding(new Insets(5, 5, 5, 5));
				vb.setSpacing(10);

				vb.getChildren().addAll(imageView, btn);

				tp.setContent(vb);

				cPane.getChildren().add(tp);

				btn.setOnAction((event) -> {
					try {
						System.out.println(c.getIdContact().getIdContactPK());
						if (c.getIdContact().getIdContactPK() == idUserLoggedIn) {
							delegate.deleteUser(c.getIdContact().getIdUserPK(), idUserLoggedIn);
							cPane.getChildren().remove(tp);
							System.out.println("first pane case1");
						} else {
							delegate.deleteUser(idUserLoggedIn, c.getIdContact().getIdContactPK());
							cPane.getChildren().remove(tp);
							System.out.println("first pane case2");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		reqPane.setPadding(new Insets(5, 5, 5, 5));
		reqPane.setVgap(4);
		reqPane.setHgap(4);

		try {
			receivedRequestsList = delegate.getRequests(idUserLoggedIn, 0);
			for (Contact c : receivedRequestsList) {

				// receivedRequestsList = delegate.getRequests(1, 0);
				// System.out.println(receivedRequestsList);
				// for (Contact c : receivedRequestsList) {

				TitledPane tp = new TitledPane();
				tp.setMaxSize(140, 140);
				tp.setMinSize(140, 140);

				User u1 = new User();
				u1 = delegate.findUserById(c.getIdContact().getIdUserPK());
				String name = new String(u1.getFirstName() + " " + u1.getLastName());
				tp.setText(name);

				ImageView imageView = new ImageView(new Image("/slim/images/" + u1.getPicture()));
				imageView.setFitHeight(50);
				imageView.setPreserveRatio(true);
				imageView.setClip(new Circle(25, 25, 25));

				ImageView okIcon = new ImageView(new Image("/slim/images/Ok-icon.png"));
				okIcon.setFitHeight(10);
				okIcon.setFitWidth(10);

				JFXButton btn = new JFXButton("Add", okIcon);

				ImageView deleteIcon = new ImageView(new Image("/slim/images/delete-icon.png"));
				deleteIcon.setFitHeight(10);
				deleteIcon.setFitWidth(10);

				JFXButton btn2 = new JFXButton("Decline", deleteIcon);

				btn2.setOnAction((event) -> {
					try {
						System.out.println(c.getIdContact().getIdContactPK());
						if (c.getIdContact().getIdContactPK() == idUserLoggedIn) {
							delegate.deleteUser(c.getIdContact().getIdUserPK(), idUserLoggedIn);
							reqPane.getChildren().remove(tp);
							System.out.println("first pane case1");
						} else {
							delegate.deleteUser(idUserLoggedIn, c.getIdContact().getIdContactPK());
							reqPane.getChildren().remove(tp);
							System.out.println("first pane case2");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

				VBox vb = new VBox();
				vb.setAlignment(Pos.CENTER);
				vb.setPadding(new Insets(5, 5, 5, 5));
				vb.setSpacing(10);

				HBox hb = new HBox();
				hb.getChildren().addAll(btn, btn2);

				vb.getChildren().addAll(imageView, hb);

				tp.setContent(vb);

				reqPane.getChildren().add(tp);

				btn.setOnAction((event) -> {
					try {
						// System.out.println(c.getIdContact().getIdUserPK());
						delegate.updateContact(c.getIdContact().getIdUserPK(), idUserLoggedIn);
						reqPane.getChildren().remove(tp);
						TitledPane tp2 = new TitledPane();
						btn2.setText("Unfriend");
						
						btn2.setOnAction((event2) -> {
							try {
								if (c.getIdContact().getIdContactPK() == idUserLoggedIn) {
									System.out.println("case1" + c.getIdContact().getIdUserPK());
									delegate.deleteUser(c.getIdContact().getIdUserPK(), idUserLoggedIn);
								} else {
									System.out.println("case2" + c.getIdContact().getIdContactPK());
									delegate.deleteUser(idUserLoggedIn, c.getIdContact().getIdContactPK());
								}
								cPane.getChildren().remove(tp2);
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
						
						tp2.setText(name);
						tp2.setMaxSize(140, 140);
						tp2.setMinSize(140, 140);
						VBox vbox = new VBox();
						vbox.setAlignment(Pos.CENTER);
						vbox.setPadding(new Insets(5, 5, 5, 5));
						vbox.setSpacing(10);
						vbox.getChildren().addAll(imageView, btn2);
						tp2.setContent(vbox);
						cPane.getChildren().add(tp2);

						btn2.setOnAction((event3) -> {
							try {
								System.out.println(c.getIdContact().getIdContactPK());
								if (c.getIdContact().getIdContactPK() == idUserLoggedIn) {
									delegate.deleteUser(c.getIdContact().getIdUserPK(), idUserLoggedIn);
									cPane.getChildren().remove(tp2);
									System.out.println("first pane case1");
								} else {
									delegate.deleteUser(idUserLoggedIn, c.getIdContact().getIdContactPK());
									cPane.getChildren().remove(tp2);
									System.out.println("first pane case2");
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						});

					} catch (Exception e) {
						e.printStackTrace();
					}
				});

			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		penPane.setPadding(new Insets(5, 5, 5, 5));
		penPane.setVgap(4);
		penPane.setHgap(4);

		try {
			sentRequestsList = delegate.getPendingRequests(idUserLoggedIn, 0);
			for (Contact c : sentRequestsList) {

				TitledPane tp = new TitledPane();
				tp.setMaxSize(140, 140);
				tp.setMinSize(140, 140);

				User u1 = new User();
				u1 = delegate.findUserById(c.getIdContact().getIdContactPK());
				String name = new String(u1.getFirstName() + " " + u1.getLastName());
				tp.setText(name);

				ImageView imageView = new ImageView(new Image("/slim/images/" + u1.getPicture()));
				imageView.setFitHeight(50);
				imageView.setPreserveRatio(true);
				imageView.setClip(new Circle(25, 25, 25));

				ImageView deleteIcon = new ImageView(new Image("/slim/images/delete-icon.png"));
				deleteIcon.setFitHeight(10);
				deleteIcon.setFitWidth(10);

				JFXButton btn = new JFXButton("Cancel request", deleteIcon);

				VBox vb = new VBox();
				vb.setAlignment(Pos.CENTER);

				vb.getChildren().addAll(imageView, btn);

				tp.setContent(vb);
				vb.setPadding(new Insets(5, 5, 5, 5));
				vb.setSpacing(10);

				tp.setContent(vb);

				penPane.getChildren().add(tp);

				btn.setOnAction((event) -> {
					try {
						delegate.deleteUser(idUserLoggedIn, c.getIdContact().getIdContactPK());
						penPane.getChildren().remove(tp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sPane.setPadding(new Insets(5, 5, 5, 5));
		sPane.setVgap(4);
		sPane.setHgap(4);

		try {
			contactList = delegate.getContactsByUserId(idUserLoggedIn);
			for (Contact c : contactList) {
				// receivedRequestsList = delegate.getRequests(1, 0);
				// System.out.println(receivedRequestsList);
				// for (Contact c : receivedRequestsList) {

				TitledPane tp = new TitledPane();
				tp.setMaxSize(140, 140);
				tp.setMinSize(140, 140);

				User u1 = new User();
				u1 = delegate.findUserById(c.getIdContact().getIdContactPK());
				String name = new String(u1.getFirstName() + " " + u1.getLastName());
				tp.setText(name);

				ImageView imageView = new ImageView(new Image("/slim/images/" + u1.getPicture()));
				imageView.setFitHeight(50);
				imageView.setPreserveRatio(true);
				imageView.setClip(new Circle(25, 25, 25));

				ImageView deleteIcon = new ImageView(new Image("/slim/images/Ok-icon.png"));
				deleteIcon.setFitHeight(10);
				deleteIcon.setFitWidth(10);

				JFXButton btn = new JFXButton("Add", deleteIcon);

				VBox vb = new VBox();
				vb.setAlignment(Pos.CENTER);

				vb.getChildren().addAll(imageView, btn);

				tp.setContent(vb);

				sPane.getChildren().add(tp);

			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		toggole();

	}

	private void hidePanes() {
		requestPane.setVisible(false);
		pendingPane.setVisible(false);
		searchPane.setVisible(false);
	}

	private void fillTreeView() {
		ArrayList<TreeItem> menu = new ArrayList<TreeItem>();

		TreeItem contacts = new TreeItem("My network contacts");
		TreeItem requests = new TreeItem("Friendship requests");
		TreeItem pending = new TreeItem("My Pending requests");
		TreeItem search = new TreeItem("Search for a contact");

		menu.add(contacts);
		menu.add(requests);
		menu.add(pending);
		menu.add(search);

		TreeItem rootItem = new TreeItem("Contacts");
		treeView.setRoot(rootItem);

		rootItem.setExpanded(true);

		rootItem.getChildren().addAll(menu);

		treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	private void toggole() {
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> old_val,
					TreeItem<String> new_val) {
				TreeItem<String> selectedItem = new_val;
				if (selectedItem.getValue().equals("Friendship requests")) {
					networkPane.setVisible(false);
					requestPane.setVisible(true);
					pendingPane.setVisible(false);
					searchPane.setVisible(false);
				}
				if (selectedItem.getValue().equals("My network contacts")) {
					networkPane.setVisible(true);
					requestPane.setVisible(false);
					pendingPane.setVisible(false);
					searchPane.setVisible(false);
				}
				if (selectedItem.getValue().equals("My Pending requests")) {
					networkPane.setVisible(false);
					requestPane.setVisible(false);
					pendingPane.setVisible(true);
					searchPane.setVisible(false);
				}
				if (selectedItem.getValue().equals("Search for a contact")) {
					networkPane.setVisible(false);
					requestPane.setVisible(false);
					pendingPane.setVisible(false);
					searchPane.setVisible(true);
				}

			}

		});
	}

}
