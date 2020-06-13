package model.cards;

public class Description implements Runnable {
    private String descriptionText;

    public Description(String descriptionText){
        if(descriptionText.contains("Divine Sheild")){
            //do something
        }
    }

    @Override
    public void run() {
         //For Each hero you can declare something
    }
}
