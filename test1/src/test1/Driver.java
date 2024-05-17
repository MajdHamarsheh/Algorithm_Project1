package test1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
import java.io.StringReader;

import test1.CustomArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Driver extends Application {

	private CustomArrayList<City> cities;

	private int numCities;
	private String startCity;
	private String endCity;
	private int[][] dp;
	private int[][] path;
	private TextArea DPtable;
	private Button browsebtn;
	private Button writebtn;
	private Button nextbtn;

	private Label showDP;
	private Label showMinCostAndPath;
	private Label showOtherPaths;

	private Button backbtn;

	private TextArea expectedResult;
	private TextField filename;
	private TextArea alternatives;
	private Label underletstravel;

	private Button travelbtn;
	private TextArea fileContent;

	private Label letsTravel;

	@Override
	public void start(Stage primaryStage) throws Exception {

		DPtable = new TextArea();
		browsebtn = new Button("CHOOSE FILE");
		writebtn = new Button("WRITE CONTENT");
		nextbtn = new Button("NEXT");

		showDP = new Label("DP TABLE");
		showMinCostAndPath = new Label("THE EXPECTED RESULT");
		showOtherPaths = new Label("THE ALTERNATIVE RESULTS");

		backbtn = new Button("BACK");

		expectedResult = new TextArea();
		filename = new TextField();
		filename.setVisible(false);
		fileContent = new TextArea();
		fileContent.setVisible(false);
		alternatives = new TextArea();

		letsTravel = new Label("Let's Travel           ");
		underletstravel = new Label("choose how to enter your trip details                      ");

		browsebtn.setStyle("-fx-background-color: #00a79d; -fx-text-fill: white; -fx-background-radius: 20px;");

		browsebtn.setStyle("-fx-background-color: #00a79d; -fx-text-fill: white; -fx-background-radius: 20px;");

		browsebtn.setOnMouseEntered(e -> {
			browsebtn.setStyle("-fx-background-color: #00877b; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		browsebtn.setOnMouseExited(e -> {
			browsebtn.setStyle("-fx-background-color: #00a79d; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		browsebtn.setOnMousePressed(e -> {
			browsebtn.setStyle("-fx-background-color: #005d56; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		browsebtn.setOnMouseReleased(e -> {
			browsebtn.setStyle("-fx-background-color: #00a79d; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		writebtn.setStyle("-fx-background-color: #00a79d; -fx-text-fill: white; -fx-background-radius: 20px;");

		writebtn.setStyle("-fx-background-color: #00a79d; -fx-text-fill: white; -fx-background-radius: 20px;");

		writebtn.setOnMouseEntered(e -> {
			writebtn.setStyle("-fx-background-color: #00877b; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		writebtn.setOnMouseExited(e -> {
			writebtn.setStyle("-fx-background-color: #00a79d; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		writebtn.setOnMousePressed(e -> {
			writebtn.setStyle("-fx-background-color: #005d56; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		writebtn.setOnMouseReleased(e -> {
			writebtn.setStyle("-fx-background-color: #00a79d; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		nextbtn.setStyle("-fx-background-color: #f7941e; -fx-text-fill: white; -fx-background-radius: 20px;");

		nextbtn.setOnMouseEntered(e -> {
			nextbtn.setStyle("-fx-background-color: #d87f00; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		nextbtn.setOnMouseExited(e -> {
			nextbtn.setStyle("-fx-background-color: #f7941e; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		nextbtn.setOnMousePressed(e -> {
			nextbtn.setStyle("-fx-background-color: #b36200; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		nextbtn.setOnMouseReleased(e -> {
			nextbtn.setStyle("-fx-background-color: #f7941e; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		backbtn.setStyle("-fx-background-color: #f7941e; -fx-text-fill: white; -fx-background-radius: 20px;");

		backbtn.setOnMouseEntered(e -> {
			backbtn.setStyle("-fx-background-color: #d87f00; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		backbtn.setOnMouseExited(e -> {
			backbtn.setStyle("-fx-background-color: #f7941e; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		backbtn.setOnMousePressed(e -> {
			backbtn.setStyle("-fx-background-color: #b36200; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		backbtn.setOnMouseReleased(e -> {
			backbtn.setStyle("-fx-background-color: #f7941e; -fx-text-fill: white; -fx-background-radius: 20px;");
		});

		StackPane sp = new StackPane();

		letsTravel.setStyle("-fx-font-size: 35px; -fx-text-fill: #00a79d; -fx-font-family: Arial;");
		underletstravel.setStyle("-fx-font-size: 15px; -fx-text-fill: #00a79d; -fx-font-family: Arial;");
		showDP.setStyle("-fx-font-size: 20px; -fx-text-fill: #f7941e; -fx-font-family: Arial;");
		showMinCostAndPath.setStyle("-fx-font-size: 20px; -fx-text-fill: #f7941e; -fx-font-family: Arial;");
		showOtherPaths.setStyle("-fx-font-size: 20px; -fx-text-fill: #f7941e; -fx-font-family: Arial;");

		VBox vb0 = new VBox(20);
		VBox DPvb = new VBox(30);
		DPvb.getChildren().addAll(showDP, DPtable);
		VBox theResultvb = new VBox(30);
		theResultvb.getChildren().addAll(showMinCostAndPath, expectedResult);
		VBox alternativesvb = new VBox(30);
		alternativesvb.getChildren().addAll(showOtherPaths, alternatives);

		HBox shows = new HBox(40);
		shows.getChildren().addAll(DPvb, theResultvb, alternativesvb);
		shows.setAlignment(Pos.CENTER);
		backbtn.setAlignment(Pos.BOTTOM_RIGHT);

		HBox layouthb = new HBox();
		HBox layouthb1 = new HBox();
		HBox layouthb2 = new HBox();
		HBox lo = new HBox();
		HBox lo1 = new HBox();
		HBox lo2 = new HBox();
		HBox lo3 = new HBox();
		HBox lo4 = new HBox();
		HBox lo5 = new HBox();
		HBox lo6 = new HBox();
		HBox lo7 = new HBox();
		HBox lo8 = new HBox();
		HBox lo9 = new HBox();
		HBox lo10 = new HBox();
		HBox lo11 = new HBox();
		HBox lo12 = new HBox();

		HBox fileBtns = new HBox(20);
		VBox btnandpath = new VBox(20);
		btnandpath.getChildren().addAll(browsebtn, filename);
		VBox btnandcontent = new VBox(20);
		btnandcontent.getChildren().addAll(writebtn, fileContent);

		fileBtns.getChildren().addAll(lo, lo1, lo2, lo3, lo4, lo5, lo6, lo7, lo8, lo9, lo10, lo11, lo12, layouthb,
				layouthb2, btnandpath, btnandcontent);

		vb0.getChildren().addAll(layouthb1, letsTravel, underletstravel, fileBtns, nextbtn);

		fileBtns.setAlignment(Pos.CENTER);
		nextbtn.setAlignment(Pos.CENTER);

		vb0.setAlignment(Pos.TOP_CENTER);
		Image backgroundImage = new Image("C:\\Users\\Ibrah\\OneDrive\\Desktop\\world.png");

		ImageView backgroundView = new ImageView(backgroundImage);
		backgroundView.setFitWidth(1000);
		backgroundView.setFitHeight(600);

		sp.setStyle("-fx-background-image: url('file:C:/Users/Ibrah/OneDrive/Desktop/world.png'); "
				+ "-fx-background-size: cover;");
		sp.getChildren().add(vb0);

		nextbtn.setOnAction(e -> {
			StackPane sp2 = new StackPane();
			sp2.getChildren().addAll(backgroundView, shows, backbtn);
			Scene scene2 = new Scene(sp2, 1000, 600);

			primaryStage.setTitle("OPTIONS");
			primaryStage.setScene(scene2);
			primaryStage.show();
			String filePath = filename.getText();
			String fileContentStr = fileContent.getText();

			if (!filePath.isEmpty()) {
				readingDataFromFile(filePath);
				calculateOptimumCost();
				displayResults();
			}
			String fileContentstr = fileContent.getText();
			if (!fileContentstr.isEmpty()) {
				readingDataFromFileContent(fileContentstr);
				calculateOptimumCost();
				displayResults();
			}

		});
		browsebtn.setOnAction(event -> {
			filename.setVisible(true);
			chooseFile();
		});

		writebtn.setOnAction(event -> {
			fileContent.setVisible(true);
		});
		// Set up the scene
		Scene scene = new Scene(sp, 1000, 600);

		// Set up the stage
		primaryStage.setTitle("MAIN");
		primaryStage.setScene(scene);
		primaryStage.show();
		backbtn.setOnAction(e -> {

			primaryStage.setScene(scene);

			primaryStage.show();
		});

	}

	private void chooseFile() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File file = fileChooser.showOpenDialog(filename.getScene().getWindow());
		if (file != null) {
			String filePath = file.getAbsolutePath();
			filename.setText(filePath);
		}

	}

	private void readingDataFromFile(String file) {
		cities = new CustomArrayList<City>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			numCities = Integer.parseInt(br.readLine().trim());

			String[] startEnd = br.readLine().split(", ");
			startCity = startEnd[0];
			endCity = startEnd[1];

			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(", ");

				String cityName = parts[0];
				City city = null;

				for (City existingCity : cities) {
					if (existingCity.getName().equals(cityName)) {
						city = existingCity;
						break;
					}
				}

				if (city == null) {
					city = new City(cityName);
					cities.add(city);
				}

				for (int i = 1; i < parts.length; i++) {
					String[] adjacentParts = parts[i].substring(1, parts[i].length() - 1).split(",");
					String adjacentName = adjacentParts[0];
					int petrolCost = Integer.parseInt(adjacentParts[1]);

					int hotelCost = Integer.parseInt(adjacentParts[2].replaceAll("[^0-9]", ""));

					AdjacentCity adjacentCity = new AdjacentCity(adjacentName, petrolCost, hotelCost);
					city.addAdjacent(adjacentCity);
				}
			}

			// Add end city
			cities.add(new City(endCity));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readingDataFromFileContent(String fileContentStr) {
		cities = new CustomArrayList<City>();

		try (BufferedReader br = new BufferedReader(new StringReader(fileContentStr))) {
			numCities = Integer.parseInt(br.readLine().trim());

			String[] startEnd = br.readLine().split(", ");
			startCity = startEnd[0];
			endCity = startEnd[1];

			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(", ");

				String cityName = parts[0];
				City city = null;

				for (City existingCity : cities) {
					if (existingCity.getName().equals(cityName)) {
						city = existingCity;
						break;
					}
				}

				if (city == null) {
					city = new City(cityName);
					cities.add(city);
				}

				for (int i = 1; i < parts.length; i++) {
					String[] adjacentParts = parts[i].substring(1, parts[i].length() - 1).split(",");
					String adjacentName = adjacentParts[0];
					int petrolCost = Integer.parseInt(adjacentParts[1]);
					int hotelCost = Integer.parseInt(adjacentParts[2].replaceAll("[^0-9]", ""));

					AdjacentCity adjacentCity = new AdjacentCity(adjacentName, petrolCost, hotelCost);
					city.addAdjacent(adjacentCity);
				}
			}

			// Add end city
			cities.add(new City(endCity));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void calculateOptimumCost() {
		dp = new int[numCities + 1][numCities + 1];
		path = new int[numCities + 1][numCities + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				if (i == j || j == 0 || i == 0) {
					dp[i][j] = 0;
				} else if (i > j) {
					dp[i][j] = Integer.MAX_VALUE;
				} else if (j > i) {
					String cityName2 = cities.get(j - 1).getName();
					City city1 = cities.get(i - 1);
					AdjacentCity adjacentCity = city1.getAdjacent(cityName2);

					if (adjacentCity != null) {
						int distance = adjacentCity.getPetrolCost() + adjacentCity.getHotelCost();
						dp[i][j] = distance;
					} else {
						dp[i][j] = Integer.MAX_VALUE;
					}
				}
			}
		}

		for (int z = 2; z < dp.length; z++) {
			for (int i = 1, j = z; i < dp.length && j < dp.length; i++, j++) {
				if (j > i + 1) {
					for (int k = i + 1; k < j; k++) {
						if (dp[i][k] == Integer.MAX_VALUE || dp[k][j] == Integer.MAX_VALUE)
							continue;
						else {
							if (dp[i][k] + dp[k][j] < dp[i][j]) {
								path[i][j] = k;
							}
							dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
						}
					}
				}
			}
		}
	}

	private void displayResults() {
		System.out.println("Displaying results...");

		CustomArrayList<Route> allPaths = getAllPathsWithCost(startCity, new CustomArrayList<>());

		// Sort the alternative routes based on their costs
		allPaths.sort(Comparator.comparingInt(Route::getCost));

		System.out.println("Number of paths found: " + allPaths.size());

		StringBuilder sb = new StringBuilder();
		int minCost = Integer.MAX_VALUE;
		String minCostPath = "";

		for (Route path : allPaths) {
			System.out.println("Path: " + path.getPath() + " (Cost: " + path.getCost() + ")");
			sb.append("Path: ").append(path.getPath()).append(" (Cost: ").append(path.getCost()).append(")")
					.append("\n");

			if (path.getCost() < minCost) {
				minCost = path.getCost();
				minCostPath = path.getPath();
			}
		}

		System.out.println("Paths to display: " + sb.toString());

		expectedResult.setText("Min Cost Path: " + minCostPath + " (Cost: " + minCost + ")\n\n");
		DPtable.setText(generateTableString(dp));
		alternatives.setText(sb.toString());
		expectedResult.setVisible(true);
		DPtable.setVisible(true);
	}

	private String getPath(int[][] path, int start, int end, CustomArrayList<City> cities) {
//        starts with the start city and recursively follows the path until
//        it reaches the end city, concatenating the city names along the way.

		if (start == end) {
			return cities.get(start - 1).getName();
		} else {
			int intermediate = path[start][end];
			if (intermediate == 0 && start != end) {
				return cities.get(start - 1).getName() + " -> " + cities.get(end - 1).getName();
			} else {
				String path1 = getPath(path, start, intermediate, cities);
				String path2 = getPath(path, intermediate, end, cities);
				if (path1.endsWith(cities.get(intermediate - 1).getName())) {
					// Remove the duplicate city from path1
					path1 = path1.substring(0, path1.lastIndexOf(cities.get(intermediate - 1).getName()));
				}
				return path1 + path2;
			}
		}
	}

	private String generateTableString(int[][] table) {
		StringBuilder sb = new StringBuilder();

		// Print the city names on the first row
		sb.append("\t\t");
		for (int i = 0; i < numCities; i++) {
			sb.append(cities.get(i).getName()).append("\t");
		}
		sb.append("\n");

		// Print the table
		for (int i = 0; i < table.length; i++) {
			if (i > 0) {
				sb.append(cities.get(i - 1).getName()).append("\t"); // Print the city name on the leftmost column
			}
			for (int j = 0; j < table[i].length; j++) {
				if (table[i][j] == Integer.MAX_VALUE) {
					sb.append("  ").append("\t");
				} else {
					sb.append(table[i][j]).append("\t");
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	private CustomArrayList<Route> getAllPathsWithCost(String currentCity, CustomArrayList<String> path) {
		System.out.println("Visiting city: " + currentCity);

		path.add(currentCity);
		if (currentCity.equals(endCity)) {
			// Found a path from start to end, calculate its cost
			int cost = calculatePathCost(path);
			System.out.println("Found path: " + concatenatePath(path) + " (Cost: " + cost + ")");
			CustomArrayList<Route> result = new CustomArrayList<>();
			result.add(new Route(concatenatePath(path), cost));
			return result;
		}

		CustomArrayList<Route> allPaths = new CustomArrayList<>();

		for (City city : cities) {
			if (city.getName().equals(currentCity)) {
				for (AdjacentCity adjacentCity : city.getAdjacents()) {
					String adjacentName = adjacentCity.getName();
					System.out.println("Adjacent city: " + adjacentName);

					// Avoiding revisiting cities in the current path to prevent cycles
					if (!path.contains(adjacentName)) {
						// Create a new list to hold the path
						CustomArrayList<String> newPath = new CustomArrayList<>();
						// Copy elements from the existing path to the new path
						for (String cityName : path) {
							newPath.add(cityName);
						}
						// Add the adjacent city to the new path
						newPath.add(adjacentName);
						// Recursively find all paths starting from the adjacent city
						CustomArrayList<Route> paths = getAllPathsWithCost(adjacentName, newPath);

						allPaths.addAll(paths);
					}
				}
				break;
			}
		}

		return allPaths;
	}

	private String concatenatePath(CustomArrayList<String> path) {
		StringBuilder sb = new StringBuilder();
		String previousCity = null;
		for (String cityName : path) {
			if (previousCity != null && cityName.equals(previousCity)) {
				// Skip repeated cities
				continue;
			}
			if (sb.length() > 0) {
				sb.append(" -> ");
			}
			sb.append(cityName);
			previousCity = cityName;
		}
		return sb.toString();
	}

	private int calculatePathCost(CustomArrayList<String> path) {
		int cost = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			String cityName = path.get(i);
			String nextCityName = path.get(i + 1);
			for (City city : cities) {
				if (city.getName().equals(cityName)) {
					AdjacentCity adjacentCity = city.getAdjacent(nextCityName);
					if (adjacentCity != null) {
						// Add the petrol and hotel costs to the total cost
						cost += adjacentCity.getPetrolCost() + adjacentCity.getHotelCost();
						break;
					}
				}
			}
		}
		return cost;
	}

	public static void main(String[] args) {
		launch(args);
	}
}