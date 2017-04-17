package bridgelabz.app.com.listviewswipetest.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bridgelabz.app.com.listviewswipetest.Database.Database;
import bridgelabz.app.com.listviewswipetest.R;
import bridgelabz.app.com.listviewswipetest.model.DataModel;
import bridgelabz.app.com.listviewswipetest.ui.TodoHomeActivity;

/**
 * Created by bridgeit on 8/4/17.
 */
public class TodoFragmentAddNote extends Fragment  {
    AppCompatEditText Title;
    AppCompatEditText Description;
    AppCompatButton save_button;
    TodoHomeActivity todohomeactivity;

    public TodoFragmentAddNote(TodoHomeActivity todoHomeActivity)
    {
        this.todohomeactivity=todoHomeActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.todonoteaddfragment, container, false);
        Title = (AppCompatEditText) view.findViewById(R.id.edittext_title);
        Description = (AppCompatEditText) view.findViewById(R.id.edittext_description);
        save_button = (AppCompatButton) view.findViewById(R.id.save_button);

        if (savedInstanceState == null)
        {
        }

        save_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DataModel dataModel=new DataModel(Title.getText().toString(),Description.getText().toString());
                Database db=new Database(getActivity());
                db.addItem(dataModel);
                todohomeactivity.setBackData(dataModel);
                todohomeactivity.fab.setVisibility(View.VISIBLE);
                getActivity().getSupportFragmentManager().popBackStackImmediate();

            }
        });
        return view;
    }

}


