package cn.ingenic.glasssync;

import java.util.List;

import com.sctek.smartglasses.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Message;
public class ShowBtAdapter extends BaseAdapter{

    private LayoutInflater inflater ;
    private List<BluetoothDevice> mList;
    private Context context;
    private Handler mHandler;
    private int BIND_TIME_OUT = 20*1000;

    public ShowBtAdapter(List<BluetoothDevice> item,Context context1, Handler handler) {
	this.mList = item;
	this.context = context1;
	inflater = LayoutInflater.from(context);
	mHandler = handler;
    }

    @Override
	public int getCount() {
	return mList.size();
    }

    @Override
	public Object getItem(int arg0) {
	return arg0;
    }

    @Override
	public long getItemId(int arg0) {
	return 0;
    }

    @Override
    public View getView(final int arg0, View convertView, ViewGroup arg2) {

	View view  = inflater.inflate(R.layout.other_bind_activity_item, null);
	TextView tv = (TextView)view.findViewById(R.id.bt_name);
	TextView address = (TextView)view.findViewById(R.id.bt_address);
	Button button = (Button)view.findViewById(R.id.bt_connect);

	tv.setText(mList.get(arg0).getName());
	address.setText(mList.get(arg0).getAddress());
	button.setOnClickListener(new View.OnClickListener() {				
		@Override
		    public void onClick(View v) {
		    Message msg = mHandler.obtainMessage();
		    msg.what = BindGlassActivity.BIND_TIMEOUT;
		    mHandler.sendMessageDelayed(msg,BIND_TIME_OUT);

		    Message requestBindMsg = mHandler.obtainMessage();
		    requestBindMsg.what = BindGlassActivity.CONNECT;
		    requestBindMsg.obj = mList.get(arg0).getAddress();
		    mHandler.sendMessage(requestBindMsg);

		}
	    });
	return view;
    }
}
