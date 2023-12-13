package deu.ac.kr.csw.chatting.friends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import deu.ac.kr.csw.chatting.R;
import deu.ac.kr.csw.chatting.user.model.User;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {

    ArrayList<User> friends;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView friendName;
        TextView friendStatus;
        ImageView friendProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendName = (TextView) itemView.findViewById(R.id.friends_list_tile_name);
            friendStatus = (TextView) itemView.findViewById(R.id.friends_list_tile_status);
            //friendProfile = (ImageView) itemView.findViewById(R.id.friends_list_tile_image);
        }

        public TextView getFriendName() {
            return friendName;
        }

        public TextView getFriendStatus() {
            return friendStatus;
        }

//        public ImageView getFriendProfile() {
//            return friendProfile;
//        }
    }

    /**
     * 친구 목록을 보여주는 어댑터 생성자
     *
     * @param friends 친구 목록
     */
    public FriendListAdapter(ArrayList<User> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_tile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User friend = friends.get(position);
        holder.getFriendName().setText(friend.getName());
        holder.getFriendStatus().setText(friend.getStatusMessage());
        //holder.getFriendProfile().setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
