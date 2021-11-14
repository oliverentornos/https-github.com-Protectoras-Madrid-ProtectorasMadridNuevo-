package com.miguel.protectorasmadrid;

import android.nfc.Tag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.miguel.protectorasmadrid.databinding.FragmentHomeBinding;

public class ModalBottomSheet extends BottomSheetDialogFragment {
    static String TAG = "Modal";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_bottom_sheet, container, false);




        Button btnPequeño = v.findViewById(R.id.btnPequeño);
        btnPequeño.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioFragment.chipTamano.setChipBackgroundColorResource(R.color.secondarytextColor);
                InicioFragment.chipTamano.setText("Pequeño");
                InicioFragment.chipTamano.setChecked(true);
                InicioFragment.chipTamano.setCloseIconVisible(true);
                dismiss();

            }
        });
        return v;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
