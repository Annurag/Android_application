package com.example.c2converter;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.style.BackgroundColorSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener {
	Spinner s1;
	static Spinner s2;
	Button b1;
	TextView t1,t2,t3;
	EditText e1;
	String enter, display;
	final String [] convert={"TEMPERATURE","LENGTH","WEIGTH","TIME"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        s1=(Spinner) findViewById(R.id.spinner1);
        s2=(Spinner) findViewById(R.id.spinner2);
        t1=(TextView) findViewById(R.id.textView1);
        t2= (TextView) findViewById(R.id.textView4);
        t3=(TextView) findViewById(R.id.TextView5);
        e1=(EditText) findViewById(R.id.editText1);
        b1=(Button) findViewById(R.id.button1);
        
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, convert);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(this);
    }
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		switch(arg2) {
			case 0:
				final String[] temperature={"CELSIUS - FAHENHEIT","FAHENHEIT - CELSIUS","CELSIUS - KELVIN","KELVIN - CELSIUS","FAHENHEIT - KELVIN","KELVIN - FAHENHEIT"};
				ArrayAdapter<String> temp=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, temperature);
				s2.setAdapter(temp);
				s2.setOnItemSelectedListener(new OnItemSelectedListener()	{
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
							switch(arg2){
								case 0:
									t2.setText("°c");
									t3.setText("°f");
									b1.setOnClickListener(new OnClickListener() {
										@SuppressWarnings("deprecation")
										@Override
										public void onClick(View v) {
											double d=collect();
											if(d==0){
												Toast.makeText(MainActivity.this, "Enter value", 1000).show();
											}else{
												double d1=(d*5/9)+32;
												display=String.valueOf(d1);
												t1.setText(display);}
											}
										});
									e1.setText("");
									t1.setText("");
									break;
								case 1:
									t2.setText("°f");
									t3.setText("°c");
									b1.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											double d=collect();
											if(d==0){
												Toast.makeText(MainActivity.this, "Enter value", 1000).show();
											}else{
												double d1=(d-32)*5/9;
												display=String.valueOf(d1);
												t1.setText(display);
												}
											}
										});
									e1.setText("");
									t1.setText("");
									break;
								case 2:
									t2.setText("°c");
									t3.setText("k");
									b1.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											double d=collect();
											if(d==0){
												Toast.makeText(MainActivity.this, "Enter value", 1000).show();
											}else{
												double d1=d+273.15;
												display=String.valueOf(d1);
												t1.setText(display);
												}
											}
										});
									e1.setText("");
									t1.setText("");
									break;
								case 3:
									t2.setText("k");
									t3.setText("°c");
									b1.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											double d=collect();
											if(d==0){
												Toast.makeText(MainActivity.this, "Enter value", 1000).show();
											}else{
												double d1=d-273.15;
												display=String.valueOf(d1);
												t1.setText(display);}
												}
											});
									e1.setText("");
									t1.setText("");
										break;
									case 4:
										t2.setText("°f");
										t3.setText("k");
										b1.setOnClickListener(new OnClickListener() {
											@Override
											public void onClick(View v) {
												double d=collect();
												if(d==0){
													Toast.makeText(MainActivity.this, "Enter value", 1000).show();
												}else{
													double d1=(d-32)*5/9+273.15;
													display=String.valueOf(d1);
													t1.setText(display);
													}
												}
											});
										e1.setText("");
										t1.setText("");
										break;
									case 5:
										t2.setText("k");
										t3.setText("°f");
										b1.setOnClickListener(new OnClickListener() {
											@Override
											public void onClick(View v) {
												double d=collect();
												if(d==0){
													Toast.makeText(MainActivity.this, "Enter value", 1000).show();
												}else{
													double d1=(d-273.15)*9/5+32;
													display=String.valueOf(d1);
													t1.setText(display);
													}
												}
											});
										e1.setText("");
										t1.setText("");
										break;
									}
								}
						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
						}
					});
				break;
			case 1:
				final String[] length={"METER - CENTIMETER","METER - KILOMETER","KILOMETER - METER","CENTIMETER - METER","CENTIMETER - INCH","INCH - CENTIMETER"};
				ArrayAdapter<String> leng=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, length);
				s2.setAdapter(leng);
				s2.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						switch(arg2){
							case 0:
								t2.setText("m");
								t3.setText("cm");
								b1.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
										double d=collect();
										if(d==0){
											Toast.makeText(MainActivity.this, "Enter value", 1000).show();
										}else{
											double d1=d*100;
											display=String.valueOf(d1);
											t1.setText(display);
											}
										}
									});
								e1.setText("");
								t1.setText("");
								break;
							case 1:
								t2.setText("m");
								t3.setText("km");
								b1.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View arg0) {
										double d=collect();
										if(d==0){
											Toast.makeText(MainActivity.this, "Enter value", 1000).show();
										}else{
											double d1=d/1000;
											display =String.valueOf(d1);
											t1.setText(display);
											}
										}
									});
								e1.setText("");
								t1.setText("");
								break;
							case 2:
								t2.setText("km");
								t3.setText("m");
								b1.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View arg0) {
										double d=collect();
										if(d==0){
											Toast.makeText(MainActivity.this, "Enter value", 1000).show();
										}else{
											double d1=d*1000;
											display =String.valueOf(d1);
											t1.setText(display);
											}
										}
									});
								e1.setText("");
								t1.setText("");
								break;
							case 3:
								t2.setText("cm");
								t3.setText("m");
								b1.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View arg0) {
										double d=collect();
										if(d==0){
											Toast.makeText(MainActivity.this, "Enter value", 1000).show();
										}else{	
											double d1=d/100;
											display =String.valueOf(d1);
											t1.setText(display);
											}
										}
									});
								e1.setText("");
								t1.setText("");
								break;
							case 4:
								t2.setText("cm");
								t3.setText("inch");
								b1.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View arg0) {
										double d=collect();
										if(d==0){
											Toast.makeText(MainActivity.this, "Enter value", 1000).show();
										}else{
											double d1=d*0.39370;
											display =String.valueOf(d1);
											t1.setText(display);
											}
										}
									});
								e1.setText("");
								t1.setText("");
								break;
							case 5:
								t2.setText("inch");
								t3.setText("cm");
								b1.setOnClickListener(new OnClickListener() {
									public void onClick(View arg0) {
										double d=collect();
										if(d==0){
											Toast.makeText(MainActivity.this, "Enter value", 1000).show();
										}else{
											double d1=d/0.39370;
											display =String.valueOf(d1);
											t1.setText(display);
											}
										}
									});
								e1.setText("");
								t1.setText("");
								break;
								}
							}
						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
						}
					});
				break;
			case 2:
				String[] weigth={"GRAM - KILOGRAM","KILOGRAM - GRAM","KILOGRAM - TONNE", "TONNE - KILOGRAM","MILLIGRAM - GRAM","GRAM - MILLIGRAM"};
				ArrayAdapter<String> weig=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, weigth);
				s2.setAdapter(weig);
				s2.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						switch(arg2){
							case 0: 
								t2.setText("gm");
								t3.setText("kg");
								b1.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View arg0) {
										double d=collect();
										if(d==0){
											Toast.makeText(MainActivity.this, "Enter value", 1000).show();
										}else{
											double d1=d/1000;
											display =String.valueOf(d1);
								t1.setText(display);
								}
							}
						});
								e1.setText("");
								t1.setText("");
						break;
					case 1:
						t2.setText("kg");
						t3.setText("gm");
						b1.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								double d=collect();
								if(d==0){
									Toast.makeText(MainActivity.this, "Enter value", 1000).show();
								}else{
								double d1=d*1000;
								display =String.valueOf(d1);
								t1.setText(display);
								}
							}
						});
						e1.setText("");
						t1.setText("");
						break;
					case 2:
						t2.setText("kg");
						t3.setText("ton");
						b1.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								double d=collect();
								if(d==0){
									Toast.makeText(MainActivity.this, "Enter value", 1000).show();
								}else{
								double d1=d/100;
								display =String.valueOf(d1);
								t1.setText(display);}
							}
						});
						e1.setText("");
						t1.setText("");
						break;
					case 3:
						t2.setText("ton");
						t3.setText("kg");
						b1.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								double d=collect();
								if(d==0){
									Toast.makeText(MainActivity.this, "Enter value", 1000).show();
								}else{
								double d1=d*100;
								display =String.valueOf(d1);
								t1.setText(display);
								}
							}
						});
						e1.setText("");
						t1.setText("");
						break;
					case 4:
						t2.setText("mg");
						t3.setText("gm");
						b1.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								double d=collect();
								if(d==0){
									Toast.makeText(MainActivity.this, "Enter value", 1000).show();
								}else{
								double d1=d/1000;
								display =String.valueOf(d1);
								t1.setText(display);
								}
							}
						});
						e1.setText("");
						t1.setText("");
						break;
					case 5:
						t2.setText("gm");
						t3.setText("mg");
						b1.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								double d=collect();
								if(d==0){
									Toast.makeText(MainActivity.this, "Enter value", 1000).show();
								}else{
								double d1=d*1000;
								display =String.valueOf(d1);
								t1.setText(display);
								}
							}
						});
						e1.setText("");
						t1.setText("");
						break;
					}
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				}
			});
			break;
			case 3:
				final String[] time={"MILLISECOND - SECOND","SECOND - MINUTE","MINUTE - HOUR","HOUR - DAY","DAY - WEEK","WEEK - MONTH","MONTH - YEAR","SECOND - MILLISECOND","MINUTE - SECOND","HOUR - MINUTE","DAY - HOUR","WEEK - DAY","MONTH - WEEK","YEAR- MONTH"};
				ArrayAdapter<String> tm=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, time);
				s2.setAdapter(tm);
				s2.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						switch(arg2){
						case 0:
							t2.setText("ms");
							t3.setText("sec");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 1000).show();
									}else{
										double d1=d/1000;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 1:
							t2.setText("sec");
							t3.setText("min");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 1000).show();
									}else{
										double d1=d/60;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 2 :
							t2.setText("min");
							t3.setText("hour");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 1000).show();
									}else{
										double d1=d/60;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 3:
							t2.setText("hour");
							t3.setText("day");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 1000).show();
									}else{
										double d1=d/24;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 4:
							t2.setText("day");
							t3.setText("week");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 1000).show();
									}else{
										double d1=d/168;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 5:
							t2.setText("week");
							t3.setText("mon");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 2000).show();
									}else{
										double d1=d/4.345238;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 6:
							t2.setText("mon");
							t3.setText("year");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 2000).show();
									}else{
										double d1=d/12;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 7:
							t2.setText("sec");
							t3.setText("ms");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 2000).show();
									}else{
										double d1=d*1000;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 8:
							t2.setText("min");
							t3.setText("sec");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 2000).show();
									}else{
										double d1=d*60;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 9:
							t2.setText("hour");
							t3.setText("min");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 2000).show();
									}else{
										double d1=d*60;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 10:
							t2.setText("day");
							t3.setText("hour");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 2000).show();
									}else{
										double d1=d*24;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 11:
							t2.setText("week");
							t3.setText("day");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 2000).show();
									}else{
										double d1=d*168;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 12:
							t2.setText("mon");
							t3.setText("week");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 2000).show();
									}else{
										double d1=d*4.345238;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						case 13:
							t2.setText("year");
							t3.setText("mon");
							b1.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									double d=collect();
									if(d==0){
										Toast.makeText(MainActivity.this, "Enter value", 2000).show();
									}else{
										double d1=d*12;
										display=String.valueOf(d1);
										t1.setText(display);
										}
									}
								});
							e1.setText("");
							t1.setText("");
							break;
						}						
					}
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
	public double collect(){
		enter =e1.getText().toString();
		double d=0;
		if (enter.length()==0){
		}else
			  d =Double.parseDouble(enter);
		return d;
	}
}
