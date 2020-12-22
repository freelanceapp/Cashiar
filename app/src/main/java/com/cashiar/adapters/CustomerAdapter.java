package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.CustomerRowBinding;
import com.cashiar.models.SingleCustomerSuplliersModel;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewholder> {

    private List<SingleCustomerSuplliersModel> list;
    private Context context;

    public CustomerAdapter(Context context, List<SingleCustomerSuplliersModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomerRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.customer_row, parent, false);
        return new CustomerViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewholder holder, int position) {
holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class CustomerViewholder extends RecyclerView.ViewHolder {
        CustomerRowBinding binding;

        public CustomerViewholder(@NonNull CustomerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
