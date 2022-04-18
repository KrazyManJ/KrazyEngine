package me.KrazyManJ.KrazyEngine.Any.Text;

public class PlaceholderBuilder {

    private final BracketHolder holder;
    private String text;

    public PlaceholderBuilder(String text) {
        this.text = text;
        holder = new BracketHolder("","");
    }
    public PlaceholderBuilder(String text,BracketHolder holder){
        this.text = text;
        this.holder = holder;
    }

    public PlaceholderBuilder add(String placeholder, String value){
        text = text.replace(toBrackets(placeholder),value);
        return this;
    }

    public String result(){
        return text;
    }

    private String toBrackets(String t){
        return holder.opening()+t+holder.closing();
    }
}
