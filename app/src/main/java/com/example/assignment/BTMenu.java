package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

public class BTMenu extends AppCompatActivity {

    private ActionMode mActionMode;
    TextView context_floating_menu, context_action_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btmenu);

        context_floating_menu = findViewById(R.id.context_floating_menu);
        registerForContextMenu(context_floating_menu);

        context_action_mode = findViewById(R.id.context_action_mode);
        context_action_mode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) return false;

                mActionMode = startSupportActionMode(mActionModeCallback);

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, DisplayActivity.class);
        switch (item.getItemId()){
            case R.id.item1:
            case R.id.item2:
            case R.id.subitem1:
            case R.id.subitem2:
                intent.putExtra("options_item", item.getTitle().toString() + " Selected");
                intent.putExtra("action", "btmenu_options");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_floating_menu_example, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, DisplayActivity.class);
        switch (item.getItemId()){
            case R.id.item1:
            case R.id.item2:
                intent.putExtra("floating_item", item.getTitle().toString() + " Selected");
                intent.putExtra("action", "btmenu_floating");
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.action_mode_example, menu);
            mode.setTitle("Choose Action");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Intent intent = new Intent(BTMenu.this, DisplayActivity.class);
            switch (item.getItemId()){
                case R.id.item1:
                    intent.putExtra("action_item", "Reminder icon selected");
                    intent.putExtra("action", "btmenu_action");
                    mode.finish();
                    startActivity(intent);
                    return true;
                case R.id.item2:
                    intent.putExtra("action_item", "Delete item selected");
                    intent.putExtra("action", "btmenu_action");
                    mode.finish();
                    startActivity(intent);
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    public void showPopup(View view){
        PopupMenu popup = new PopupMenu(this, view);
        popup.inflate(R.menu.popup_menu_example);
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(BTMenu.this, DisplayActivity.class);
                switch (item.getItemId()){
                    case R.id.item1:
                    case R.id.item2:
                    case R.id.item3:
                    case R.id.item4:
                        intent.putExtra("popup_item", item.getTitle().toString() + " Selected");
                        intent.putExtra("action", "btmenu_popup");
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

}