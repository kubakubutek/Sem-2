//bla bla
interface metody {
    public abstract double obwod();
    public abstract double pole();
    public abstract void wypisz();
    
}

abstract class figura implements metody{
   
    
}
abstract class czworokat extends figura{
    public double bok1;
    public double bok2;
    public double bok3;
    public double bok4;
    public double kat;
}

class kolo extends figura{
    public double promien;
kolo(double r){
    promien =r;
}
public double pole(){
    return (Math.PI*promien*promien);
}
public double obwod(){
    return 2*Math.PI*promien;    
}
public void wypisz(){    
    System.out.println("Pole: "+pole());
    System.out.println("Obwod: "+obwod());
    System.out.println("Kolo");
}
}

class pieciokat extends figura{
public double bok;
pieciokat(double b){
    bok = b;
}
public double pole(){
    return ((Math.sqrt(25+(10*Math.sqrt(5))))/4)*bok*bok;    
}
public double obwod(){
    return 5*bok;    
}

public void wypisz(){    
    System.out.println("Pole: "+pole());
    System.out.println("Obwod: "+obwod());
    System.out.println("Pieciokat");
}

}
class szeciokat extends figura {
public double bok;
szeciokat(double b){
    bok = b;
}

public double pole(){
    return ((3*Math.sqrt(3)*bok*bok)/2);    
}

public double obwod(){
    return 6*bok;    
}

public void wypisz(){    
    System.out.println("Pole: "+pole());
    System.out.println("Obwod: "+obwod());
    System.out.println("Szesciokat");
}
}

class prostokat extends czworokat{
prostokat(double b1,double b2,double b3, double b4,double k){
    bok1 = b1;
    bok2 = b2;
    bok3 = b3;
    bok4 = b4;
    kat = k;
}
public double pole(){
    if(bok1!=bok2){
    return bok1*bok2;    
    }
    else{        
        return bok1*bok3;
    }    
}
public double obwod(){
    return bok1+bok2+bok3+bok4;    
}
public void wypisz(){    
    System.out.println("Pole: "+pole());
    System.out.println("Obwod: "+obwod());
    System.out.println("Prostokat");
}
    
}
class kwadrat extends czworokat{
    kwadrat(double b1,double b2,double b3, double b4,double k){
        bok1 = b1;
        bok2 = b2;
        bok3 = b3;
        bok4 = b4;
        kat = k;
    }
public double pole(){
    return bok1*bok1;    
}
public double obwod(){
    return 4*bok1;    
}
public void wypisz(){    
    System.out.println("Pole: "+pole());
    System.out.println("Obwod: "+obwod());
    System.out.println("Kwadrat");
}
    
}
class romb extends czworokat{
    romb(double b1,double b2,double b3, double b4,double k){
        bok1 = b1;
        bok2 = b2;
        bok3 = b3;
        bok4 = b4;
        kat = k;
    }

public double pole(){
    return bok1*bok1*Math.sin(kat);
}

public double obwod(){
    return 4*bok1;    
}

public void wypisz(){    
    System.out.println("Pole: "+pole());
    System.out.println("Obwod: "+obwod());
    System.out.println("Romb");
}
}

public class Figury{

    public static void main(String[] args){
        try {
            figura f;

            if(args.length<2){
                throw new Exception("Za malo argumentow");
            }
            if(args.length>6){
                throw new Exception("Za duzo argumentow");
            }

            switch (args[0].charAt(0)) {
                case 'o':
                    if(Double.parseDouble(args[1])<=0){
                        throw new Exception("Zbyt mala liczba");
                    }
                    f = new kolo(Double.parseDouble(args[1]));
                    break;
                case 'p':
                if(Double.parseDouble(args[1])<=0){
                    throw new Exception("Zbyt mala liczba");
                }
                f= new pieciokat(Double.parseDouble(args[1]));
                    break;
                case 's':
                if(Double.parseDouble(args[1])<=0){
                    throw new Exception("Zbyt mala liczba");
                }
                f= new szeciokat(Double.parseDouble(args[1]));
                    break;  
                case 'c':
                if(args.length==6){
                    if((Double.parseDouble(args[1])<=0d)||(Double.parseDouble(args[2])<=0d)||(Double.parseDouble(args[3])<=0d)||(Double.parseDouble(args[4])<=0d)){throw new Exception("Zbyt mala liczba");}
                    else if(Double.parseDouble(args[5])==90d)
                    {
                        if(Double.parseDouble(args[1])==Double.parseDouble(args[2])&&Double.parseDouble(args[2])==Double.parseDouble(args[3])&&Double.parseDouble(args[3])==Double.parseDouble(args[4])){
                            f= new kwadrat(Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]),Double.parseDouble(args[4]),Double.parseDouble(args[5]));
                        }
                        else{
                            f= new prostokat(Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]),Double.parseDouble(args[4]),Double.parseDouble(args[5]));
                        }
                    }else{
                    f= new romb(Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]),Double.parseDouble(args[4]),Double.parseDouble(args[5]));
                }
            }
                else{
                    throw new Exception("Zla ilosc argumentow");
                }
                    break;
            
                default:
                throw new Exception("Zle argumenty");
                    


                    
            }
            f.wypisz();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

}