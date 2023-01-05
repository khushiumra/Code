package com.example.first;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter {

    static class NoteViewHolder extends RecyclerView.ViewHolder
    {
        TextView textTile, textSubtitle, textDateTime;

        NoteViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textTile = itemView.findViewById(R.id.textTitle);
            textSubtitle = itemView.findViewById(R.id.textSubtitle);
            textDateTime = itemView.findViewById(R.id.textDateTime);
        }
    }
}
