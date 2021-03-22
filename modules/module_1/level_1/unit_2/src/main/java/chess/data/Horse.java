package chess.data;

public final class Horse {
    private static Horse instance;
    private final Object[] current_place = new Object[2];

    public static Horse getInstance() {
        if(instance == null){
            instance = new Horse();
        }
        return instance;
    }

    public void setPosition(Object[] array) {
       current_place[0] = array[0];
       current_place[1] = array[1];
    }

    public Object[] getPosition() {
        return current_place;
    }

    public String toString(){
        return "" + current_place[0] + current_place[1];
    }
}