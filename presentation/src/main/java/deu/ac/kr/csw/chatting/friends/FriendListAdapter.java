package deu.ac.kr.csw.chatting.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import deu.ac.kr.csw.chatting.R;
import deu.ac.kr.csw.chatting.user.model.User;

public class FriendListAdapter extends BaseAdapter {

    List<User> friends;

    public FriendListAdapter(List<User> friends) {
        this.friends = friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return friends.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.friends_list_tile, parent, false);

        ImageView profileImageView = view.findViewById(R.id.friends_list_tile_image);
        TextView nameTextView = view.findViewById(R.id.friends_list_tile_name);
        TextView statusTextView = view.findViewById(R.id.friends_list_tile_status);

        User friend = friends.get(position);

        Picasso.get().load(friend.getAvatar()).into(profileImageView);
        nameTextView.setText(friend.getName());
        statusTextView.setText(friend.getStatusMessage());

        return view;
    }
}
