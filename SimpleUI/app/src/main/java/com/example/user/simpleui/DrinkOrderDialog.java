package com.example.user.simpleui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.RadialGradient;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.DialogPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.net.PortUnreachableException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnDrinkOrderListener} interface
 * to handle interaction events.
 * Use the {@link DrinkOrderDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinkOrderDialog extends DialogFragment {

    static final String DRINK_PARAM ="drink";

    DrinkOrder drinkOrder;

    private OnDrinkOrderListener mListener;

    NumberPicker mediumNumberPicker;
    NumberPicker largeNuberPicker;
    RadioGroup sugarRadioGroup;
    RadioGroup iceRadioGroup;
    EditText noteEditText;

    public DrinkOrderDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment DrinkOrderDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static DrinkOrderDialog newInstance(DrinkOrder drinkOrder) {
        DrinkOrderDialog fragment = new DrinkOrderDialog();
        Bundle args = new Bundle();

        args.putParcelable(DRINK_PARAM,drinkOrder);

        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//        }
//        mListener.OnDrinkOrderFinished();
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_drink_order_dialog, container, false);
//    }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            if (getArguments() != null)
            {
                drinkOrder = getArguments().getParcelable(DRINK_PARAM);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View contect = getActivity().getLayoutInflater().inflate(R.layout.fragment_drink_order_dialog, null);
            builder.setView(contect)
                    .setTitle(drinkOrder.getDrink().getName())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            drinkOrder.setlNumber(largeNuberPicker.getValue());
                            drinkOrder.setmNumber(mediumNumberPicker.getValue());
                            drinkOrder.setIce(getSeleectedItemFromRadioGroup(iceRadioGroup));
                            drinkOrder.setSugar(getSeleectedItemFromRadioGroup(sugarRadioGroup));
                            drinkOrder.setNote(noteEditText.getText().toString());

                            if (mListener != null) {
                                mListener.OnDrinkOrderFinished(drinkOrder);
                            }

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            mediumNumberPicker = (NumberPicker)contect.findViewById(R.id.mediumNumberPicker);
            largeNuberPicker = (NumberPicker)contect.findViewById(R.id.largeNumderPicker);
            iceRadioGroup = (RadioGroup)contect.findViewById(R.id.iceRadioGroup);
            sugarRadioGroup = (RadioGroup)contect.findViewById(R.id.sugarGadioGroup);
            noteEditText = (EditText)contect.findViewById(R.id.noteEditText);

            mediumNumberPicker.setMaxValue(100);
            mediumNumberPicker.setMinValue(0);
            mediumNumberPicker.setValue(drinkOrder.getmNumber());
            largeNuberPicker.setMaxValue(100);
            largeNuberPicker.setMinValue(0);
            largeNuberPicker.setValue(drinkOrder.getlNumber());

            noteEditText.setText(drinkOrder.getNote());

            setSelectedItemInRadioGroup(drinkOrder.getIce(),iceRadioGroup);

            setSelectedItemInRadioGroup(drinkOrder.getSugar(),sugarRadioGroup);

            return builder.create();
            }

    private void setSelectedItemInRadioGroup(String selectedItem,RadioGroup radioGroup)
    {
        int count = radioGroup.getChildCount();
        for(int i = 0; i <count ; i++)
        {
            View view = radioGroup.getChildAt(i);
            if(view instanceof RadioButton)
            {
                RadioButton radioButton = (RadioButton)view;
                if(radioButton.getText().toString().equals(selectedItem))
                {
                    radioButton.setChecked(true);
                }
                else
                {
                    radioButton.setChecked(false);
                }
            }
        }
    }
    private  String getSeleectedItemFromRadioGroup(RadioGroup radioGroup)
    {
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton button = (RadioButton)radioGroup.findViewById(radioButtonID);
        return  button.getText().toString();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDrinkOrderListener) {
            mListener = (OnDrinkOrderListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDrinkOrderListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnDrinkOrderListener {
        // TODO: Update argument type and name
        void OnDrinkOrderFinished(DrinkOrder drinkOrder);
    }
}



