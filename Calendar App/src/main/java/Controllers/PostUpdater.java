package Controllers;

import java.util.ArrayList;
import java.util.Iterator;

import ApiClient.PostApiClient;
import Observers.Observer;
import Observers.Subject;
import Posts.Post;

public class PostUpdater implements Subject {

    private ArrayList<Observer> observers;
    private ArrayList<Post> posts;
    private PostApiClient client;

    public PostUpdater (){
        observers = new ArrayList<>();
        client = PostApiClient.getInstance();
        posts = client.getPosts();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Iterator<Observer> iterator = observers.iterator(); iterator.hasNext(); ){
            Observer observer = iterator.next();
            observer.update();
        }
    }

    public ArrayList<Post> getPosts (){
        return this.posts;
    }

    public void addPost (Post post){
        client.addPost(post);
        this.posts.add(0, post);
        notifyObservers();
    }
}