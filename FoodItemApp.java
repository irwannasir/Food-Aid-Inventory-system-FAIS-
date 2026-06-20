import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;


public class FoodItemApp {
    public static void main(String[] args) throws Exception{
      Scanner in = new Scanner(System.in);
      Scanner inLine = new Scanner(System.in);
      ArrayList<FoodItem> foodItem = new ArrayList<>();
      try {
          System.out.println("PROGRAM STARTED...");
            
            
            BufferedReader br = new BufferedReader(new FileReader(new File("fileinputFAIS.txt")));
            String data = br.readLine();
            while(data != null) {
                StringTokenizer token = new StringTokenizer(data, ",");
                if(token.countTokens() >= 5) {
                    
                
                String id = token.nextToken().trim();
                String name = token.nextToken().trim();
                int quantity = Integer.parseInt(token.nextToken().trim());
                LocalDate dateReceived = LocalDate.parse(token.nextToken().trim());
                LocalDate expirationDate = LocalDate.parse(token.nextToken().trim());

                FoodItem food = new FoodItem(id, name, quantity, dateReceived, expirationDate);
                foodItem.add(food);
                }
                data = br.readLine();
            }
            
            //bubble sort method
            for(int x = 0 ; x < foodItem.size() - 1 ; x++) {
              for(int y = 0 ; y < foodItem.size() - 1 - x ; y++) {
                FoodItem f1 = foodItem.get(y);
                FoodItem f2 = foodItem.get(y+1);

                if(f1.getName().compareTo(f2.getName()) > 0) {
                  foodItem.set(y, f2);
                  foodItem.set(y+1, f1);

                }
              }
            }
            //display food list depends on alphabet  ( buat function )
            
            //interface 
            boolean continueLoop = true;
            do {
              System.out.println("############################################");
              System.out.println("WELCOME TO FOOD AID INVENTORY SYSTEM");
              System.out.println("############################################");

              System.out.println("1. Display Food Item");
              System.out.println("2. Search Food Item");
              System.out.println("3. Display All Food Items based on Expiration Date");
              System.out.println("4. Update Food Item ");
              System.out.println("5. Remove Food Item");
              System.out.println("6. Add food Item");
              System.out.println("7. Exit");
              System.out.print("Enter your choice: ");
              int choice = in.nextInt();
              in.nextLine(); // Consume newline

              switch (choice) {
                 case 1:
                 // display food item 
                 System.out.println();
                 System.out.println("List of Food in Ascending Alphabet :");
                 for(int x = 0 ; x < foodItem.size() ; x++) {
                      System.out.print(foodItem.get(x).toString());
                 }
                 break;
                 case 2:
                 //search food item
                 //binary search (buat function )
       
                 System.out.println("Enter Food Name : ");
                 String food = in.nextLine();

                 String key = food;
                 int low = 0;
                 int high = foodItem.size() - 1;
                 int result = -1;
                 while(high >= low) {
                     int mid = (low + high) / 2;
                     int compare = key.compareToIgnoreCase(foodItem.get(mid).getName());

                     if(compare == 0) {
                        result = mid; //found the food that user want
                        break;
                      }
                      else if(compare < 0) {
                        high = mid - 1; //to search from the left
                      }
                      else{ 
                        low = mid + 1; //to search from the right
                      }

                 }
                 if(result != -1) {
                     System.out.println("Item Found : "+foodItem.get(result).toString());
                 }
                 else {
                     System.out.println("Item not found.");
                 }
                      break;
                  case 3:
                      // display food items based on expiration date
                      Queue<FoodItem> foodQueueExpired = new Queue<>();
                      Queue<FoodItem> foodQueue = new Queue<>();
                      for(int x = 0 ; x < foodItem.size() ; x++) {
                          LocalDate today = LocalDate.now();
                          FoodItem expired = foodItem.get(x);
                          LocalDate max = null;
                          if(expired.getExpirationDate().isBefore(today)) {
                              foodQueueExpired.enqueue(expired);
                          }
                          else if(expired.getExpirationDate().isEqual(today)){
                              foodQueueExpired.enqueue(expired);
                          }
                          else {
                              if(expired.getExpirationDate().isAfter(today)) {
                                  foodQueue.enqueue(expired);
                              }
                          }
                      }
                      //foodQueue.enqueue(foodItem);
                      System.out.print("Expired Item :");
                      System.out.println(foodQueueExpired.toString());
                      System.out.println();
                      System.out.print("Not Expired Item : ");
                      System.out.println(foodQueue.toString());
                      break;
                  case 4:
                      //bubble sort method
                    for(int x = 0 ; x < foodItem.size() - 1 ; x++) {
                      for(int y = 0 ; y < foodItem.size() - 1 - x ; y++) {
                        FoodItem f1 = foodItem.get(y);
                        FoodItem f2 = foodItem.get(y+1);
    
                        if(f1.getName().compareTo(f2.getName()) > 0) {
                          foodItem.set(y, f2);
                          foodItem.set(y+1, f1);

                        }
                      }
                    }
                      // update food item
                      System.out.println("Enter food name to be update : ");
                     String updateFood = in.nextLine();

                     String target = updateFood;
                     int min = 0;
                     int max = foodItem.size() - 1;
                     int found = -1;
                     while(max >= min) {
                         int mid = (min + max) / 2;
                         int compare = target.compareToIgnoreCase(foodItem.get(mid).getName());

                         if(compare == 0) {
                            found = mid; //found the food that user want
                            break;
                          }
                          else if(compare < 0) {
                            max = mid - 1; //to search from the left
                          }
                          else{ 
                            min = mid + 1; //to search from the right
                          }

                     }
                     if(found != -1) {
                         System.out.println("Choose part to update :");
                         System.out.println("1. Food ID");
                         System.out.println("2. Food Name");
                         System.out.println("3. Quantity");
                         System.out.println("4. Date Received ");
                         System.out.println("5. Date Expiration");
                         int part = in.nextInt();
                         in.nextLine(); // Consume newline
                     
                         switch(part) {
                             case 1:
                                 //update ID
                                 System.out.println("Enter new ID : ");
                                 String id = in.nextLine();
                                 foodItem.get(found).setId(id);
                                 System.out.println("Updated Successfully");
                                 break;
                             case 2:
                                 //update name
                                 System.out.println("Enter new Food Name : ");
                                 String n = in.nextLine();
                                 foodItem.get(found).setName(n);
                                 System.out.println("Updated Successfully");
                                 break;
                             case 3:
                                 //update quantity
                                 System.out.println("Enter new Quantity : ");
                                 int q = inLine.nextInt();
                                 foodItem.get(found).setQuantity(q);
                                 System.out.println("Updated Successfully");
                                 break;
                             case 4:
                                 //update date received
                                 System.out.println("Enter new Date Received : ");
                                 LocalDate dr = LocalDate.parse(in.nextLine());
                                 foodItem.get(found).setDateReceived(dr);
                                 System.out.println("Updated Successfully");
                                 break;
                             case 5:
                                 //update expiration date
                                 System.out.println("Enter new Expiration Date : ");
                                 LocalDate de = LocalDate.parse(in.nextLine());
                                 foodItem.get(found).setDateReceived(de);
                                 System.out.println("Updated Successfully");
                                 
                                 break;
                             default:
                                 System.out.println("Invalid choice.");

                         }
                     
                     }
                     else {
                         System.out.println("Item not found.");
                     }
                      
                     break;
                  case 5:
                      // remove food item
                      //bubble sort
                  for(int x = 0 ; x < foodItem.size() - 1 ; x++) {
                      for(int y = 0 ; y < foodItem.size() - 1 - x ; y++) {
                        FoodItem f1 = foodItem.get(y);
                        FoodItem f2 = foodItem.get(y+1);

                        if(f1.getName().compareTo(f2.getName()) > 0) {
                          foodItem.set(y, f2);
                          foodItem.set(y+1, f1);

                        }
                      }
                    }
                    // update food item
                      System.out.println("Enter food name to be remove : ");
                     String removeFood = in.nextLine();
                   String sasaran = removeFood;
                     int rendah = 0;
                     int tinggi = foodItem.size() - 1;
                     int jumpa = -1;
                     while(tinggi >= rendah) {
                         int mid = (tinggi + rendah) / 2;
                         int compare = sasaran.compareToIgnoreCase(foodItem.get(mid).getName());

                         if(compare == 0) {
                            jumpa = mid; //found the food that user want
                            break;
                          }
                          else if(compare < 0) {
                            tinggi = mid - 1; //to search from the left
                          }
                          else{ 
                            rendah = mid + 1; //to search from the right
                          }

                     }
                     if(jumpa != -1) {
                           System.out.println("Are you sure want to remove this item ?");
                           String pilihan = in.nextLine();
                           if(pilihan.equalsIgnoreCase("Yes")) {
                                foodItem.remove(jumpa);
                                System.out.println("remove successfully");
                           }
                           
                        }
                      break;
                    
                 case 6:
                     //add food item
                     System.out.println("ADDING NEW FOOD");
                     System.out.println("Enter Food ID : ");
                     String id = in.nextLine();
                     System.out.println("Enter Food Name : ");
                     String n = in.nextLine();
                     System.out.println("Enter Food Quantity : ");
                     int q = Integer.parseInt(in.nextLine());
                     System.out.println("Enter Date Received : ");
                     LocalDate dr = LocalDate.parse(in.nextLine());
                     System.out.println("Enter Date Expiration : ");
                     LocalDate de = LocalDate.parse(in.nextLine());
                     FoodItem newFood = new FoodItem(id, n, q, dr, de);
                     foodItem.add(newFood);
                     break;
                  case 7:
                      System.out.println("Exiting...");
                      continueLoop = false;
                      break;
                  default:
                      System.out.println("Invalid choice. Please try again.");
              }
              
              if(continueLoop) {
                  System.out.println();
                  System.out.print("Do you want to continue? (yes/no): ");
                  String continueChoice = in.nextLine();
                  continueLoop = continueChoice.equalsIgnoreCase("Yes");
              }
              
            } while (continueLoop);
            System.out.println("Thank You For Using Our Service");
            br.close();

        
        
        


      } 
      
      catch (Exception e) {
          System.err.println(e.getMessage()); 
          e.printStackTrace();
      }

       finally {

      }

      

    } 

}
