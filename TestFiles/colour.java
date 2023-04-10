import java.awt.Color;

public class colour {
    private Color[] mColors;
    private int colorcount;
    private Color[] newcolors;
    private int newcolorcount;
  
	// List of Colors
	  public colour() {

      mColors = new Color[13];
      mColors[0] = new Color(255, 0, 0);
		  mColors[1] = new Color(255, 153, 0);
		  mColors[2] = new Color(255, 255, 0);
		  mColors[3] = new Color(102, 255, 51);
		  mColors[4] = new Color(51, 204, 204);
		  mColors[5] = new Color(0, 0, 255);
		  mColors[6] = new Color(204, 51, 153);
		  mColors[7] = new Color(255, 0, 255);
		  mColors[8] = new Color(255, 204, 255);
		  mColors[9] = new Color(160, 160, 160);
		  mColors[10] = new Color(100, 100, 100);
		  mColors[11] = new Color(255, 255, 255);
		  mColors[12] = new Color(0, 0, 0);
		
		  colorcount = 13;
    }
    // Method to get a random color
	public Color getRandomColor() {
		int c = (int)(Math.random()*mColors.length);
		return mColors[c];
	}
	
	// Method to get a specific color
	public Color getColor(int c){
		return mColors[c];
	}
	
	// Method to get total color count
	public int GetColorCount(){
		return colorcount;
	}

    public void Newcolour() {
        newcolors = new Color[57];

        mColors[0] = new Color(0xac, 0xc2, 0xd9);  //  cloudy blue 
        mColors[1] = new Color(0x56, 0xae, 0x57);  //  dark pastel green 
        mColors[2] = new Color(0xb2, 0x99, 0x6e);  //  dust  
        mColors[3] = new Color(0xa8, 0xff, 0x04);  //  electric lime 
        mColors[4] = new Color(0x69, 0xd8, 0x4f);  //  fresh green 
        mColors[5] = new Color(0x89, 0x45, 0x85);  //  light eggplant  
        mColors[6] = new Color(0x70, 0xb2, 0x3f);  //  nasty green 
        mColors[7] = new Color(0xd4, 0xff, 0xff);  //  really light blue 
        mColors[8] = new Color(0x65, 0xab, 0x7c);  //  tea 
        mColors[9] = new Color(0x95, 0x2e, 0x8f);  //  warm purple 
        mColors[10] = new Color(0xfc, 0xfc, 0x81);  //  yellowish tan 
        mColors[11] = new Color(0xa5, 0xa3, 0x91);  //  cement  
        mColors[12] = new Color(0x38, 0x80, 0x04);  //  dark grass green  
        mColors[13] = new Color(0x4c, 0x90, 0x85);  //  dusty teal  
        mColors[14] = new Color(0x5e, 0x9b, 0x8a);  //  grey teal 
        mColors[15] = new Color(0xef, 0xb4, 0x35);  //  macaroni and cheese 
        mColors[16] = new Color(0xd9, 0x9b, 0x82);  //  pinkish tan 
        mColors[17] = new Color(0x0a, 0x5f, 0x38);  //  spruce  
        mColors[18] = new Color(0x0c, 0x06, 0xf7);  //  strong blue 
        mColors[19] = new Color(0x61, 0xde, 0x2a);  //  toxic green 
        mColors[20] = new Color(0x37, 0x78, 0xbf);  //  windows blue  
        mColors[21] = new Color(0x22, 0x42, 0xc7);  //  blue blue 
        mColors[22] = new Color(0x53, 0x3c, 0xc6);  //  blue with a hint of purple  
        mColors[23] = new Color(0x9b, 0xb5, 0x3c);  //  booger  
        mColors[24] = new Color(0x05, 0xff, 0xa6);  //  bright sea green  
        mColors[25] = new Color(0x1f, 0x63, 0x57);  //  dark green blue 
        mColors[26] = new Color(0x01, 0x73, 0x74);  //  deep turquoise  
        mColors[27] = new Color(0x0c, 0xb5, 0x77);  //  green teal  
        mColors[28] = new Color(0xff, 0x07, 0x89);  //  strong pink 
        mColors[29] = new Color(0xaf, 0xa8, 0x8b);  //  bland 
        mColors[30] = new Color(0x08, 0x78, 0x7f);  //  deep aqua 
        mColors[31] = new Color(0xdd, 0x85, 0xd7);  //  lavender pink 
        mColors[32] = new Color(0xa6, 0xc8, 0x75);  //  light moss green  
        mColors[33] = new Color(0xa7, 0xff, 0xb5);  //  light seafoam green 
        mColors[34] = new Color(0xc2, 0xb7, 0x09);  //  olive yellow  
        mColors[35] = new Color(0xe7, 0x8e, 0xa5);  //  pig pink  
        mColors[36] = new Color(0x96, 0x6e, 0xbd);  //  deep lilac  
        mColors[37] = new Color(0xcc, 0xad, 0x60);  //  desert  
        mColors[38] = new Color(0xac, 0x86, 0xa8);  //  dusty lavender  
        mColors[39] = new Color(0x94, 0x7e, 0x94);  //  purpley grey  
        mColors[40] = new Color(0x98, 0x3f, 0xb2);  //  purply  
        mColors[41] = new Color(0xff, 0x63, 0xe9);  //  candy pink  
        mColors[42] = new Color(0xb2, 0xfb, 0xa5);  //  light pastel green  
        mColors[43] = new Color(0x63, 0xb3, 0x65);  //  boring green  
        mColors[44] = new Color(0x8e, 0xe5, 0x3f);  //  kiwi green  
        mColors[45] = new Color(0xb7, 0xe1, 0xa1);  //  light grey green  
        mColors[46] = new Color(0xff, 0x6f, 0x52);  //  orange pink 
        mColors[47] = new Color(0xbd, 0xf8, 0xa3);  //  tea green 
        mColors[48] = new Color(0xd3, 0xb6, 0x83);  //  very light brown  
        mColors[49] = new Color(0xff, 0xfc, 0xc4);  //  egg shell 
        mColors[50] = new Color(0x43, 0x05, 0x41);  //  eggplant purple 
        mColors[51] = new Color(0xff, 0xb2, 0xd0);  //  powder pink 
        mColors[52] = new Color(0x99, 0x75, 0x70);  //  reddish grey  
        mColors[53] = new Color(0xad, 0x90, 0x0d);  //  baby shit brown 
        mColors[54] = new Color(0xc4, 0x8e, 0xfd);  //  liliac  
        mColors[55] = new Color(0x50, 0x7b, 0x9c);  //  stormy blue 
        mColors[56] = new Color(0x7d, 0x71, 0x03);  //  ugly brown

        newcolorcount = 56;

    }
    public Color getRandomnewColor() {
		int a = (int)(Math.random()*newcolors.length);
		return newcolors[a];
    }
    public Color getNewColor(int a){
      return newcolors[a];
    }
    public int GetNewColorCount(){
		return newcolorcount;
	}
}
