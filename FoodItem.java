
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FoodItem {
    private String id; 
    private String name;
    private int quantity;
    private LocalDate dateReceived;
    private LocalDate expirationDate;

    public FoodItem(String id, String name, int quantity, LocalDate dateReceived, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.dateReceived = dateReceived;
        this.expirationDate = expirationDate;
    }

    //getter
    public String getId() {
        return id;
     }
     public String getName() {
        return name;
     }
     public int getQuantity() {
        return quantity;
     }
     public LocalDate getDateReceived() {
            return dateReceived;
        }

     public LocalDate getExpirationDate() {
            return expirationDate;
        }

    //setter
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDateReceived(LocalDate dateReceived) {
        this.dateReceived = dateReceived;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "\nFoodItem [id=" + id + ", name=" + name + ", quantity=" + quantity + 
               ", dateReceived=" + dateReceived.format(formatter) + 
               ", expirationDate=" + expirationDate.format(formatter) + "]";
    }
    
    

}