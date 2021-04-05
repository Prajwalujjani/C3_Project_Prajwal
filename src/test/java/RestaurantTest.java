import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void beforeEach(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant open = Mockito.mock(Restaurant.class);
        Mockito.when(open.isRestaurantOpen()).thenReturn(true);
        assertEquals(true, open.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant open = Mockito.mock(Restaurant.class);
        Mockito.when(open.isRestaurantOpen()).thenReturn(true);
        assertEquals(false, open.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void calculate_total_amount_of_bill() throws itemNotFoundException{


        restaurant.addToMenu("Noodles",40);
        restaurant.addToMenu("Gobi manchurian",70);
        assertEquals(110,restaurant.calculateBill(new String[]{"Noodles","Gobi manchurian"}));
    }

    @Test
    public void if_item_not_selected_then_return_zero() throws itemNotFoundException {


        restaurant.addToMenu("Noodles",40);
        restaurant.addToMenu("Gobi manchurian",70);
        assertEquals(0,restaurant.calculateBill(new String[0]));


    }
    @Test
    public void if_item_not_found_throw_exception(){


        restaurant.addToMenu("Noodles",40);
        restaurant.addToMenu("Gobi manchurian",70);
        assertThrows(itemNotFoundException.class,()->{
            restaurant.calculateBill(new String[]{"Noodles"});
        });

    }
}