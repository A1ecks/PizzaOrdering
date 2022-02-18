package application;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * 
 * @author Alex Yang
 *
 */
public class Menu{
	private static ArrayList<MenuItem> Pizzas;
	private ArrayList<Button> Description;
	private ArrayList<Text> PizzaInfo;
	private ArrayList<Text> Price;
	private Pizza pepPizza, sausPizza, spPizza, supremePizza, veggiePizza, hawaiianPizza;
	private Order order;
	private Customer customer;
	
	private Font headerFont;
	private StackPane combineLOGO;
	private VBox orderList;
	private Group pepMenu, sausMenu, spMenu, supMenu, vegMenu, hwMenu, Header, PizzaMenu;	
	private Button submitInfo, back, checkout, cart, changeInfo, reOrder, pepAdd, sausAdd, spAdd, supAdd, vegAdd, hwAdd;
	private Rectangle pepBox, sausBox, spBox, supBox, vegBox, hwBox, pepBorder, sausBorder, spBorder, supBorder, vegBorder, hwBorder, logo;
	
	private ComboBox<Crust> pepCrust, sausCrust, spCrust, supCrust, vegCrust, hwCrust;
	private ComboBox<PizzaSize> pepSize, sausSize, spSize, supSize, vegSize, hwSize;
	private Text question, orderHeading, welcome, orderType, address, logoText, numItemsText;
	private TextField firstName, lastName, addressLine, city, state, zip;
	private RadioButton delivery, takeOut;
	
	private Image pepperoniImage, sausageImage, spImage, supremeImage, veggieImage, hawaiianImage; 
	private ImageView shoppingCart, pepperoniIV, sausageIV, spIV, supremeIV, veggieIV, hawaiianIV; 
	
	/**
	 * Constructor initializes the main menu
	 */
	public Menu() { 
		customer = new Customer();
		order = new Order();
	}
	
	/**
	 * Method returns buttons for scene-switching in start method
	 * @param sceneSelect
	 * @return Button
	 */
	public Button getSwitch(int sceneSelect) {
		switch(sceneSelect) {
			case 0:
				return changeInfo;
			case 1:
				return submitInfo;
			case 2:
				return cart;
			case 3:
				return back;
			case 4:
				return checkout;
			default:
				return null;
		}
	}
	
	/**
	 * Method builds the Main Menu and returns the scene
	 * @return window
	 */
	public Pane MainMenu() {
		Pane MainMenu = new Pane(Header(), Pizza());
			processCrust();
			processSize();
		return MainMenu;
	}
	
	/**
	 * Method builds the Customer Form and returns the scene
	 * @return
	 */
	public FlowPane CustomerForm() {
		
		firstName = new TextField();
			firstName.setPrefWidth(100);
			firstName.setPromptText("First name");
			firstName.setFocusTraversable(false);
		lastName = new TextField();
			lastName.setPrefWidth(110);
			lastName.setPromptText("Last Name");
			lastName.setFocusTraversable(false);
		addressLine = new TextField();
			addressLine.setPrefWidth(110);
			addressLine.setPromptText("Address Line");
			addressLine.setFocusTraversable(false);
		city = new TextField();
			city.setPrefWidth(100);
			city.setPromptText("City");
			city.setFocusTraversable(false);
		state = new TextField();
			state.setPrefWidth(50);
			state.setPromptText("State");
			state.setFocusTraversable(false);
		zip = new TextField();
			zip.setPrefWidth(50);
			zip.setPromptText("Zip");
			zip.setFocusTraversable(false);
		
			customer.setDeliver(false);
			firstName.setDisable(true);
			lastName.setDisable(true);
			addressLine.setDisable(true);
			city.setDisable(true);
			state.setDisable(true);
			zip.setDisable(true);
			
		HBox entName = new HBox(firstName, lastName);
			entName.setSpacing(10);
		HBox region = new HBox(city, state, zip);
			region.setSpacing(10);
		VBox entAddress = new VBox(addressLine, region);
			entAddress.setSpacing(10);
		VBox input = new VBox(entName, entAddress);
			input.setSpacing(10);
			
		delivery = new RadioButton("Delivery");
		takeOut = new RadioButton("Takeout");
		delivery.setOnAction(this::processActionCust);
		takeOut.setOnAction(this::processActionCust);
		Group rbGroup = new Group(delivery, takeOut);
		ToggleGroup rButtons = new ToggleGroup();
			delivery.setToggleGroup(rButtons);
			takeOut.setToggleGroup(rButtons);
		submitInfo = new Button("OK");
			submitInfo.setFont(Font.font("Helvetica", 11));
			submitInfo.setMaxHeight(10);
			submitInfo.setMinWidth(110);
			submitInfo.setDisable(true);
			submitInfo.setOnAction(this::processActionCust);
			
		question = new Text(100, 50, "Delivery or Takeout?");
		VBox options = new VBox(question, takeOut, delivery, submitInfo);
			options.setAlignment(Pos.TOP_LEFT);
			options.setSpacing(7);
			options.setPadding(new Insets(0, 30, 10, 10));
		
		FlowPane form = new FlowPane(rbGroup, options, input);
			form.setPadding(new Insets(15, 15, 15, 15));
			
		return form;
	}
	
	/**
	 * Method builds the Order Form and returns the scene
	 * @return ScrollPane
	 */
	public ScrollPane OrderForm() {
		orderHeading = new Text("             Your Order\n===================");
			orderHeading.setFont(Font.font("Times New Roman", 32));
		orderList = new VBox();
			orderList.setSpacing(10);
			orderList.getChildren().add(orderHeading);
		Description = new ArrayList<Button>();
		PizzaInfo = new ArrayList<Text>();
		Price = new ArrayList<Text>();
		
		for (int i = 0; i < order.getItems().size(); i++) {
			Description.add(new Button(order.getItems().get(i).getDescription()));
				Description.get(i).setFont(Font.font("Times New Roman", 18));
				Description.get(i).setMaxWidth(240);
				Description.get(i).setMinWidth(240);
				Description.get(i).setOnAction(this::processActionOrder);
			PizzaInfo.add(new Text("\t" + order.getItems().get(i).getPizza().getPizzaSize() +
								   "\n\t" + order.getItems().get(i).getPizza().getCrust() + " Crust" +
								   "\n\tTopped with:\n\t\t" + order.getItems().get(i).getPizza().getToppings()));
				PizzaInfo.get(i).setFont(Font.font("Times New Roman", 16));
			Price.add(new Text("            $" + Double.toString(order.getItems().get(i).getPrice())));			
				Price.get(i).setFont(Font.font("Times New Roman", 18));
			orderList.getChildren().add(new HBox(new VBox(Description.get(i), PizzaInfo.get(i)), Price.get(i)));
		}
		Text total = new Text("===================\nTotal:\t\t\t$" + order.getTotal());
			total.setFont(Font.font("Times New Roman", 32));
			orderList.getChildren().add(total);
		checkout = new Button("Checkout");
			checkout.setFont(Font.font("Times New Roman", 32));
			checkout.setOnAction(this::processActionOrder);
		back = new Button("Menu");
			back.setFont(Font.font("Times New Roman", 32));
			back.setOnAction(this::processActionOrder);
		HBox options = new HBox(back, checkout);
			options.setSpacing(55);
		orderList.getChildren().add(options);
		
		ScrollPane form = new ScrollPane(orderList);
			form.setPadding(new Insets(20, 10, 20, 20));
		return form;
	}
	
	/**
	 * Method builds the Receipt and returns the scene
	 * @return ScrollPane
	 */
	public ScrollPane Receipt() {								
		Text reieptHeading = new Text("                Invoice\n===================");
			reieptHeading.setFont(Font.font("Times New Roman", 32));
		VBox receipt = new VBox();
			receipt.setSpacing(10);
		Text eta = new Text();
			eta.setFont(Font.font("Times New Roman", 14));
			if(this.customer.isDeliver()) {
				eta.setText("Delivery for " + customer.getName() + " in 30 minutes\nto" + customer.getAddress());
				receipt.getChildren().add(eta);
			} else {
				eta.setText("Takeout for " + customer.getName() + " in 20 minutes\nat" + customer.getAddress());
				receipt.getChildren().add(eta);
			}
			receipt.getChildren().add(reieptHeading);
		ArrayList<Text> desc = new ArrayList<Text>();
		
		for (int i = 0; i < order.getItems().size(); i++) {
			desc.add(new Text(order.getItems().get(i).getDescription()));
				desc.get(i).setFont(Font.font("Times New Roman", 18));
			receipt.getChildren().add(new HBox(new VBox(desc.get(i), PizzaInfo.get(i)),  Price.get(i)));
		}
		Text total = new Text("===================\nTotal:\t\t\t$" + order.getTotal());
			total.setFont(Font.font("Times New Roman", 32));
			receipt.getChildren().add(total);
		ScrollPane form = new ScrollPane(receipt);
			form.setPadding(new Insets(20, 10, 20, 20));
		return form;
	}
	
	/**
	 * Returns the instance of order in the class
	 * @return order
	 */
	public Order getOrder(){
		return this.order;
	}
	
	/**
	 * Returns the instance of customer in the class
	 * @return customer
	 */
	public Customer getCustomer() {
		return this.customer;
	}
	
	/**
	 * Method builds the header which has the shopping cart, customer information, and button for restarting the order
	 * @return Header
	 */
	private Group Header() {
		headerFont = Font.font("Helvetica", FontWeight.EXTRA_BOLD,18);
		
		Line headerTopL1 = new Line(354, 22, 354, 93);
			headerTopL1.relocate(354, 22);
		Line headerTopL2 = new Line(602, 22, 602, 93);
			headerTopL2.relocate(602, 22);
		Line headerTopL3 = new Line(983, 22, 983, 93);
			headerTopL3.relocate(963, 22);
		logoText = new Text("LOGO");
			logoText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 40));
			logoText.setFill(Color.WHITE);
		logo = new Rectangle(247, 73);
			logo.setFill(Color.TURQUOISE);
			logo.setArcHeight(80);
			logo.setArcWidth(80);
		shoppingCart = new ImageView(new Image("file:ShoppingCart.png"));
			shoppingCart.setFitHeight(70);
			shoppingCart.setFitWidth(70);
		numItemsText = new Text(Integer.toString(order.getItems().size()));
			numItemsText.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 32));
			numItemsText.setFill(Color.WHITE);
			numItemsText.setStyle("-fx-stroke-width: 2;-fx-stroke: black;");
		cart = new Button(Integer.toString(order.getItems().size()), shoppingCart);
			cart.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 32));
			cart.setStyle("-fx-background-color: transparent");
			cart.setOnAction(this::processAction);
			cart.relocate(980, 20);
		reOrder = new  Button("RE-ORDER");
			reOrder.setFont(headerFont);
			reOrder.setStyle("-fx-background-color: transparent");
			reOrder.relocate(423, 39);
			reOrder.setOnAction(this::processAction);
		combineLOGO = new StackPane(logo, logoText);
			Group LOGO = new Group(combineLOGO);
			LOGO.relocate(87, 22);
			
		//Will need the Order class for this
		orderType = new Text(customer.getDeliver());
			orderType.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 18));
			orderType.relocate(617, 26);
		
		//Will need the Customer class for this
		address = new Text(customer.getAddress());
			address.setFont(Font.font("Helvetica", 18));
			address.relocate(616, 48);
		
		changeInfo = new Button("Change Info");
			changeInfo.setFont(Font.font("Helvetica", 14));
			changeInfo.setStyle("-fx-background-color: transparent");
			changeInfo.relocate(617, 64);
			changeInfo.setOnAction(this::processAction);
		Line headerBottomL1= new Line(385, 125, 785, 125);
		Line headerBottomL2 = new Line(385, 171, 785, 171);
		welcome = new Text("WELCOME");
			welcome.setFont(headerFont);
			welcome.setX(542);
			welcome.setY(155);
		Header = new Group(headerTopL1, headerTopL2, headerTopL3, LOGO, orderType, address, changeInfo, reOrder, cart, headerBottomL1, headerBottomL2, welcome);
		
		return Header;
	}
	
	/**
	 * Method builds the entire selection Pizzas which contains all buttons, images, and ComboBoxes
	 * @return PizzaMenu
	 */
	private Group Pizza() {
		Pizzas = new ArrayList<MenuItem>();
				pepPizza = new Pizza(PizzaSize.Medium, Crust.Pan, Topping.Pepperoni);
				sausPizza = new Pizza(PizzaSize.Medium, Crust.Pan, Topping.Sausage);
				spPizza = new Pizza(PizzaSize.Medium, Crust.Pan, Topping.Sausage);
					spPizza.addTopping(Topping.Pepperoni);
				supremePizza = new Pizza(PizzaSize.Medium, Crust.Pan, Topping.Sausage);
					supremePizza.addTopping(Topping.BlackOlives);
					supremePizza.addTopping(Topping.GreenPeppers);
					supremePizza.addTopping(Topping.Onions);
				veggiePizza = new Pizza(PizzaSize.Medium, Crust.Pan, Topping.GreenPeppers);
					veggiePizza.addTopping(Topping.BlackOlives);
					veggiePizza.addTopping(Topping.Spinach);
					veggiePizza.addTopping(Topping.Mushrooms);
					veggiePizza.addTopping(Topping.Onions);
				hawaiianPizza = new Pizza(PizzaSize.Medium, Crust.Pan, Topping.Cheese);
					hawaiianPizza.addTopping(Topping.Pineapple);	
					hawaiianPizza.addTopping(Topping.Bacon);	// Yes, bacon. I forgot to make the art for ham.
		Pizzas.add(new MenuItem(7.99, pepPizza, "Pepperoni Pizza"));		
		Pizzas.add(new MenuItem(7.99, sausPizza, "Sausage Pizza"));
		Pizzas.add(new MenuItem(7.99, spPizza, "Sausage and Pepperoni"));
		Pizzas.add(new MenuItem(7.99, supremePizza, "Supreme Pizza"));
		Pizzas.add(new MenuItem(7.99, veggiePizza, "Veggie Pizza"));		
		Pizzas.add(new MenuItem(7.99, hawaiianPizza, "Hawaiian Pizza"));
		
		pepperoniImage = new Image("file:Pepperoni_Pizza.png");
		sausageImage = new Image("file:Sausage_Pizza.png");
		spImage = new Image("file:S&P_Pizza.png");
		supremeImage = new Image("file:Supreme_Pizza.png");
		veggieImage = new Image("file:Veggie_Pizza.png");
		hawaiianImage = new Image("file:Hawaiian_Pizza.png");
		
		
		
		pepperoniIV = new ImageView(pepperoniImage);
			pepperoniIV.setFitHeight(100);
			pepperoniIV.setFitWidth(100);
			pepperoniIV.relocate(420, 227);
		pepCrust = new ComboBox<Crust>(FXCollections.observableArrayList(Crust()));
			pepCrust.setMinSize(241, 22);
			pepCrust.relocate(135, 227);
			pepCrust.setPromptText(Pizzas.get(0).getPizza().getCrust().toString());
		pepSize = new ComboBox<PizzaSize>(FXCollections.observableArrayList(Size()));
			pepSize.setMinSize(241, 22);
			pepSize.relocate(135, 260);
			pepSize.setPromptText(Pizzas.get(0).getPizza().getPizzaSize().toString());
		pepAdd = new Button("Add Pepperoni Pizza" + " ($" + Pizzas.get(0).getPrice() + ")");
			pepAdd.setFont(Font.font("Helvectica", FontWeight.BOLD, 12));
			pepAdd.setStyle("-fx-background-color: turquoise");
			pepAdd.setMinSize(241, 22);
			pepAdd.relocate(135, 305);
			pepAdd.setOnAction(this::processAction);
		pepBox = new Rectangle(87, 208, 462, 138);
			pepBox.setFill(Color.WHITE);
			pepBorder = new Rectangle(86, 207, 464, 140);
				pepBorder.setFill(Color.BLACK);
		pepMenu = new Group(pepBorder, pepBox, pepCrust, pepSize, pepAdd, pepperoniIV);
			
		sausageIV = new ImageView(sausageImage);
			sausageIV.setFitHeight(100);
			sausageIV.setFitWidth(100);
			sausageIV.relocate(950, 227);
		sausCrust = new ComboBox<Crust>(FXCollections.observableArrayList(Crust()));
			sausCrust.setMinSize(241, 22);
			sausCrust.relocate(665, 227);
			sausCrust.setPromptText(Pizzas.get(1).getPizza().getCrust().toString());
		sausSize = new ComboBox<PizzaSize>(FXCollections.observableArrayList(Size()));
			sausSize.setMinSize(241, 22);
			sausSize.relocate(666, 260);
			sausSize.setPromptText(Pizzas.get(1).getPizza().getPizzaSize().toString());
		sausAdd = new Button("Add Sausage Pizza" + " ($" + Pizzas.get(1).getPrice() + ")");
			sausAdd.setFont(Font.font("Helvectica", FontWeight.BOLD, 12));
			sausAdd.setStyle("-fx-background-color: turquoise");
			sausAdd.setMinSize(241, 22);
			sausAdd.relocate(665, 305);
			sausAdd.setOnAction(this::processAction);
		sausBox = new Rectangle(617, 208, 462, 138);
				sausBox.setFill(Color.WHITE);
			sausBorder = new Rectangle(616, 207, 464, 140);
				sausBorder.setFill(Color.BLACK);
		sausMenu = new Group(sausBorder, sausBox, sausSize, sausCrust, sausAdd, sausageIV);
		
		spIV = new ImageView(spImage);
			spIV.setFitHeight(100);
			spIV.setFitWidth(100);
			spIV.relocate(420, 402);
		spCrust = new ComboBox<Crust>(FXCollections.observableArrayList(Crust()));
			spCrust.setMinSize(241, 22);
			spCrust.relocate(135, 405);
			spCrust.setPromptText(Pizzas.get(2).getPizza().getCrust().toString());
		spSize = new ComboBox<PizzaSize>(FXCollections.observableArrayList(Size()));
			spSize.setMinSize(241, 22);
			spSize.relocate(136, 438);
			spSize.setPromptText(Pizzas.get(2).getPizza().getPizzaSize().toString());
		spAdd = new Button("Add Sausage & Pepperoni Pizza" + " ($" + Pizzas.get(2).getPrice() + ")");
			spAdd.setFont(Font.font("Helvectica", FontWeight.BOLD, 11));
			spAdd.setStyle("-fx-background-color: turquoise");
			spAdd.setMinSize(241, 22);
			spAdd.relocate(135, 480);
			spAdd.setOnAction(this::processAction);
		spBox = new Rectangle(87, 383, 462, 138);
				spBox.setFill(Color.WHITE);
			spBorder = new Rectangle(86, 382, 464, 140);
				spBorder.setFill(Color.BLACK);
		spMenu = new Group(spBorder, spBox, spSize, spCrust, spAdd, spIV);
				
		supremeIV = new ImageView(supremeImage);
			supremeIV.setFitHeight(100);
			supremeIV.setFitWidth(100);
			supremeIV.relocate(950, 402);
		supCrust = new ComboBox<Crust>(FXCollections.observableArrayList(Crust()));
			supCrust.setMinSize(241, 22);
			supCrust.relocate(665, 405);
			supCrust.setPromptText(Pizzas.get(3).getPizza().getCrust().toString());
		supSize = new ComboBox<PizzaSize>(FXCollections.observableArrayList(Size()));
			supSize.setMinSize(241, 22);
			supSize.relocate(666, 438);
			supSize.setPromptText(Pizzas.get(3).getPizza().getPizzaSize().toString());
		supAdd = new Button("Add Supreme Pizza" + " ($" + Pizzas.get(3).getPrice() + ")");
			supAdd.setFont(Font.font("Helvectica", FontWeight.BOLD, 12));
			supAdd.setStyle("-fx-background-color: turquoise");
			supAdd.setMinSize(241, 22);
			supAdd.relocate(666, 480);
			supAdd.setOnAction(this::processAction);
		supBox = new Rectangle(617, 383, 462, 138);
				supBox.setFill(Color.WHITE);
			supBorder = new Rectangle(616, 382, 464, 140);
				supBorder.setFill(Color.BLACK);
		supMenu = new Group(supBorder, supBox, supSize, supCrust, supAdd, supremeIV);
				
		veggieIV = new ImageView(veggieImage);
			veggieIV.setFitHeight(100);
			veggieIV.setFitWidth(100);
			veggieIV.relocate(420, 577);
		vegCrust = new ComboBox<Crust>(FXCollections.observableArrayList(Crust()));
			vegCrust.setMinSize(241, 22);
			vegCrust.relocate(136, 580);
			vegCrust.setPromptText(Pizzas.get(4).getPizza().getCrust().toString());
		vegSize = new ComboBox<PizzaSize>(FXCollections.observableArrayList(Size()));
			vegSize.setMinSize(241, 22);
			vegSize.relocate(136, 613);
			vegSize.setPromptText(Pizzas.get(4).getPizza().getPizzaSize().toString());
		vegAdd = new Button("Add Vegetable Pizza" + " ($" + Pizzas.get(4).getPrice() + ")");
			vegAdd.setFont(Font.font("Helvectica", FontWeight.BOLD, 12));
			vegAdd.setStyle("-fx-background-color: turquoise");
			vegAdd.setMinSize(241, 22);
			vegAdd.relocate(135, 655);
			vegAdd.setOnAction(this::processAction);
		vegBox = new Rectangle(87, 558, 462, 138);
				vegBox.setFill(Color.WHITE);
			vegBorder = new Rectangle(86, 557, 464, 140);
				vegBorder.setFill(Color.BLACK);
		vegMenu = new Group(vegBorder, vegBox, vegSize, vegCrust, vegAdd, veggieIV);
				
		hawaiianIV = new ImageView(hawaiianImage);
			hawaiianIV.setFitHeight(100);
			hawaiianIV.setFitWidth(100);
			hawaiianIV.relocate(950, 577);
		hwCrust = new ComboBox<Crust>(FXCollections.observableArrayList(Crust()));
			hwCrust.setMinSize(241, 22);
			hwCrust.relocate(665, 577);
			hwCrust.setPromptText(Pizzas.get(5).getPizza().getCrust().toString());
			hwCrust.setOnAction(this::processAction);
		hwSize = new ComboBox<PizzaSize>(FXCollections.observableArrayList(Size()));
			hwSize.setMinSize(241, 22);
			hwSize.relocate(665, 612);
			hwSize.setPromptText(Pizzas.get(5).getPizza().getPizzaSize().toString());
			hwSize.setOnAction(this::processAction);
		hwAdd = new Button("Add Hawaiian Pizza" + " ($" + Pizzas.get(5).getPrice() + ")");
			hwAdd.setFont(Font.font("Helvectica", FontWeight.BOLD, 12));
			hwAdd.setStyle("-fx-background-color: turquoise");
			hwAdd.setMinSize(241, 22);
			hwAdd.relocate(666, 655);
			hwAdd.setOnAction(this::processAction);
		hwBox = new Rectangle(617, 558, 462, 138);
				hwBox.setFill(Color.WHITE);
			hwBorder = new Rectangle(616, 557, 464, 140);
				hwBorder.setFill(Color.BLACK);
		hwMenu = new Group(hwBorder, hwBox, hwSize, hwCrust, hwAdd, hawaiianIV);
		
		PizzaMenu = new Group(pepMenu, sausMenu, spMenu, supMenu, vegMenu, hwMenu);
		
		return PizzaMenu;
	}
	
	/**
	 * [NOT IMPLEMENTED]
	 * Method returns an ArrayList of all possible Toppings
	 * @return AllToppings
	 */
	private ArrayList<Topping> Topping() {
		ArrayList<Topping> AllToppings = new ArrayList<Topping>();
		AllToppings.add(Topping.Pepperoni);
		AllToppings.add(Topping.Sausage);
		AllToppings.add(Topping.Bacon);
		AllToppings.add(Topping.BlackOlives);
		AllToppings.add(Topping.Cheese);
		AllToppings.add(Topping.ExtraCheese);
		AllToppings.add(Topping.GreenPeppers);
		AllToppings.add(Topping.Ham);
		AllToppings.add(Topping.Mushrooms);
		AllToppings.add(Topping.Onions);
		AllToppings.add(Topping.Pineapple);
		AllToppings.add(Topping.Spinach);
		AllToppings.add(Topping.Steak);
		return AllToppings;
	}
	
	/**
	 * Method returns an ArrayList of all possible Pizza Sizes
	 * @return AllSizes<PizzaSize>
	 */
	private ArrayList<PizzaSize> Size() {
		ArrayList<PizzaSize> AllSizes = new ArrayList<PizzaSize>();
		AllSizes.add(PizzaSize.Personal);
		AllSizes.add(PizzaSize.Small);
		AllSizes.add(PizzaSize.Medium);
		AllSizes.add(PizzaSize.Large);
		AllSizes.add(PizzaSize.ExtraLarge);
		AllSizes.add(PizzaSize.NYStyle);
		return AllSizes;
	}
	
	/**
	 * Method returns an ArrayList of all possible Crust types
	 * @return AllCrustTypes<Crust>
	 */
	private ArrayList<Crust> Crust() {
		ArrayList<Crust> AllCrustTypes = new ArrayList<Crust>();
		AllCrustTypes.add(Crust.Pan);
		AllCrustTypes.add(Crust.HandTossed);
		AllCrustTypes.add(Crust.Stuffed);
		AllCrustTypes.add(Crust.Deep);
		return AllCrustTypes;
	}
	
	/**
	 * Method updates all buttons to reflect the correct price for each combination
	 * @param button to be updated
	 * @param index of the menuItem to be updated
	 */
	private void UpdateButton(Button button, int index) {
		if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Personal && Pizzas.get(index).getPizza().getCrust() == Crust.Pan) {
		    Pizzas.get(index).setPrice(3.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Personal && Pizzas.get(index).getPizza().getCrust() == Crust.HandTossed) {
		    Pizzas.get(index).setPrice(3.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Personal && Pizzas.get(index).getPizza().getCrust() == Crust.Stuffed) {
		    Pizzas.get(index).setPrice(4.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Personal && Pizzas.get(index).getPizza().getCrust() == Crust.Deep) {
		    Pizzas.get(index).setPrice(5.99);
		}

		if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Small && Pizzas.get(index).getPizza().getCrust() == Crust.Pan) {
		      Pizzas.get(index).setPrice(5.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Small && Pizzas.get(index).getPizza().getCrust() == Crust.HandTossed) {
		      Pizzas.get(index).setPrice(5.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Small && Pizzas.get(index).getPizza().getCrust() == Crust.Stuffed) {
		      Pizzas.get(index).setPrice(6.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Small && Pizzas.get(index).getPizza().getCrust() == Crust.Deep) {
		      Pizzas.get(index).setPrice(8.99);
		}

		if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Medium && Pizzas.get(index).getPizza().getCrust() == Crust.Pan) {
		      Pizzas.get(index).setPrice(7.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Medium && Pizzas.get(index).getPizza().getCrust() == Crust.HandTossed) {
		      Pizzas.get(index).setPrice(7.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Medium && Pizzas.get(index).getPizza().getCrust() == Crust.Stuffed) {
		      Pizzas.get(index).setPrice(8.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Medium && Pizzas.get(index).getPizza().getCrust() == Crust.Deep) {
		      Pizzas.get(index).setPrice(10.99);
		}

		if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Large && Pizzas.get(index).getPizza().getCrust() == Crust.Pan) {
		      Pizzas.get(index).setPrice(9.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Large && Pizzas.get(index).getPizza().getCrust() == Crust.HandTossed) {
		      Pizzas.get(index).setPrice(9.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Large && Pizzas.get(index).getPizza().getCrust() == Crust.Stuffed) {
		      Pizzas.get(index).setPrice(10.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.Large && Pizzas.get(index).getPizza().getCrust() == Crust.Deep) {
		      Pizzas.get(index).setPrice(12.99);
		}

		if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.ExtraLarge && Pizzas.get(index).getPizza().getCrust() == Crust.Pan) {
		      Pizzas.get(index).setPrice(12.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.ExtraLarge && Pizzas.get(index).getPizza().getCrust() == Crust.HandTossed) {
		      Pizzas.get(index).setPrice(12.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.ExtraLarge && Pizzas.get(index).getPizza().getCrust() == Crust.Stuffed) {
		      Pizzas.get(index).setPrice(13.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.ExtraLarge && Pizzas.get(index).getPizza().getCrust() == Crust.Deep) {
		      Pizzas.get(index).setPrice(14.99);
		}

		if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.NYStyle && Pizzas.get(index).getPizza().getCrust() == Crust.Pan) {
		      Pizzas.get(index).setPrice(14.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.NYStyle && Pizzas.get(index).getPizza().getCrust() == Crust.HandTossed) {
		      Pizzas.get(index).setPrice(14.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.NYStyle && Pizzas.get(index).getPizza().getCrust() == Crust.Stuffed) {
		      Pizzas.get(index).setPrice(16.99);
		} else if(Pizzas.get(index).getPizza().getPizzaSize() == PizzaSize.NYStyle && Pizzas.get(index).getPizza().getCrust() == Crust.Deep) {
		      Pizzas.get(index).setPrice(17.99);
		}
		
		// If statement does a recursive scan to update prices
		Node nodeOut1 = PizzaMenu.getChildren().get(index);
		if(nodeOut1 instanceof Group){
			for(Node nodeIn:((Group)nodeOut1).getChildren()){
				if(nodeIn instanceof Button){
					button.setText(("Add " + Pizzas.get(index).getDescription() + " ($" + Pizzas.get(index).getPrice() + ")"));
				}
			}
		}
	}	

	/**
	 * Method updates name, address, and order type
	 */
	public void UpdateCustomer(){
		
		if(submitInfo.isArmed()) {
			if(delivery.isSelected()) {
				customer.setName(firstName.getText() + " " + lastName.getText());
				customer.setAddress(addressLine.getText() + " " + city.getText() + ", " + state.getText() + " " + zip.getText());
			} else if(takeOut.isSelected()) {
				customer.setName(firstName.getText() + " " +  lastName.getText());
				customer.setAddress("4231 Lipsum St Ipsum, WI 75986");
			}
		}
		
		Node nodeOut1 = Header.getChildren().get(4);
		// If statement updates the order type on the gui
		if(nodeOut1 instanceof Text){
			orderType.setText(customer.getDeliver());
		}
		
		Node nodeOut2 = Header.getChildren().get(5);
		// If statement updates the customer address on the gui
		if(nodeOut2 instanceof Text){
			address.setText(customer.getAddress());
		}
	}
	
	/**
	 * Method updates the number on the cart button, indicating the number of items in the order
	 */
	private void UpdateCart() {
		Node nodeOut = Header.getChildren().get(8);
		
		// If statement updates number of items in the order
		if(nodeOut instanceof Button)
		{
			cart.setText(Integer.toString(order.getItems().size()));
		}
	}
	
	/**
	 * Method handles all Crust related ComboBox events which updates prices and Crust types
	 */
	private void processCrust() {
		pepCrust.setOnAction(event ->
		{
				Pizzas.get(0).getPizza().setCrust(pepCrust.getValue());
				UpdateButton(pepAdd, 0);
		});
		sausCrust.setOnAction(event ->
		{
				Pizzas.get(1).getPizza().setCrust(sausCrust.getValue());
				UpdateButton(sausAdd, 1);
		});
		spCrust.setOnAction(event ->
		{
				Pizzas.get(2).getPizza().setCrust(spCrust.getValue());
				UpdateButton(spAdd, 2);
		});
		supCrust.setOnAction(event ->
		{
				Pizzas.get(3).getPizza().setCrust(supCrust.getValue());
				UpdateButton(supAdd, 3);
		});
		vegCrust.setOnAction(event ->
		{
				Pizzas.get(4).getPizza().setCrust(vegCrust.getValue());
				UpdateButton(vegAdd, 4);
		});
		hwCrust.setOnAction(event ->
		{
				Pizzas.get(5).getPizza().setCrust(hwCrust.getValue());
				UpdateButton(hwAdd, 5);
		});
	}
	
	/**
	 * Method handles all PizzaSize related ComboBox events which updates prices and Pizza Sizes
	 */
	private void processSize() {
		pepSize.setOnAction(event ->
		{
				Pizzas.get(0).getPizza().setPizzaSize(pepSize.getValue());
				UpdateButton(pepAdd, 0);
		});
		sausSize.setOnAction(event ->
		{
				Pizzas.get(1).getPizza().setPizzaSize(sausSize.getValue());
				UpdateButton(sausAdd, 1);
		});
		spSize.setOnAction(event ->
		{
				Pizzas.get(2).getPizza().setPizzaSize(spSize.getValue());
				UpdateButton(spAdd, 2);
		});
		supSize.setOnAction(event ->
		{
				Pizzas.get(3).getPizza().setPizzaSize(supSize.getValue());
				UpdateButton(supAdd, 3);
		});
		vegSize.setOnAction(event ->
		{
				Pizzas.get(4).getPizza().setPizzaSize(vegSize.getValue());
				UpdateButton(vegAdd, 4);
		});
		hwSize.setOnAction(event ->
		{
				Pizzas.get(5).getPizza().setPizzaSize(hwSize.getValue());
				UpdateButton(hwAdd, 5);
		});
	}

	/**
	 * Method handles all button events in the OrderForm scene
	 * @param event
	 */
	private void processActionOrder(ActionEvent event) {
		for(int i = 0; i < order.getItems().size(); i++) {
			if(Description.get(i).isArmed()) {
				Description.get(i).setDisable(true);
				Description.get(i).setText("[Removed]");				
				Description.remove(i);
			PizzaInfo.remove(i);
			Price.remove(i);
			order.getItems().remove(i);
			}
		}
		UpdateCart();
	}
	
	/**
	 * Method handles all button events in CustomerForm scene
	 * @param event
	 */
	private void processActionCust(ActionEvent event) {
		if(submitInfo.isArmed()) {
			if(delivery.isSelected()) {
				customer.setName(firstName.getText() + " " + lastName.getText());
				customer.setAddress(addressLine.getText() + " " + city.getText() + ", " + state.getText() + " " + zip.getText());
			} else if(takeOut.isSelected()) {
				customer.setName(firstName.getText() + " " +  lastName.getText());
				customer.setAddress( "  4231 Lipsum St Ipsum, WI 75986");
			}
			UpdateCustomer();
		}
		if (delivery.isSelected()) {
			customer.setDeliver(true);
			firstName.setDisable(false);
			lastName.setDisable(false);
			addressLine.setDisable(false);
			city.setDisable(false);
			state.setDisable(false);
			zip.setDisable(false);
			submitInfo.disableProperty().bind(firstName.textProperty().isEmpty().or(lastName.textProperty().isEmpty().or(addressLine.textProperty().isEmpty()).or(city.textProperty().isEmpty()).or(state.textProperty().isEmpty()).or(zip.textProperty().isEmpty())));
		}
		else if (takeOut.isSelected()) {
			customer.setDeliver(false);
			firstName.setDisable(false);
			lastName.setDisable(false);
			addressLine.setDisable(true);
			city.setDisable(true);
			state.setDisable(true);
			zip.setDisable(true);
			submitInfo.disableProperty().bind(firstName.textProperty().isEmpty().or(lastName.textProperty().isEmpty()));
		}
	}
	
	/**
	 * Method handles all button events in MainMenu scene
	 * @param event
	 */
	private void processAction(ActionEvent event) {
		if(pepAdd.isArmed()) {
			order.add(Pizzas.get(0));
			this.UpdateCart();
		} else if (sausAdd.isArmed()) {
			order.add(Pizzas.get(1));
			UpdateCart();
		} else if (spAdd.isArmed()) {
			order.add(Pizzas.get(2));
			UpdateCart();
		} else if (supAdd.isArmed()) {
			order.add(Pizzas.get(3));
			UpdateCart();
		} else if (vegAdd.isArmed()) {
			order.add(Pizzas.get(4));
			UpdateCart();
		} else if (hwAdd.isArmed()) {
			order.add(Pizzas.get(5));
			UpdateCart();
		}
		
		if(reOrder.isArmed()) {
			order.getItems().removeAll(Pizzas);
			UpdateCart();
		}		
	}

}
