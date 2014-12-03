import java.util.*;
import java.io.*;

final class Question{
  private final int[] question = {0,1,2,3,4,5,6,7,8,9};//問題用の配列要素[0]〜[3]を問題にする
  private int tmp;
  private int i;
  
  // Qustionの配列をランダムに変えて要素[0]〜[3]を問題にする
  public Question(){
    for (i = 0; i < question.length;i++) {
      int rand = new Random().nextInt(10);
      tmp = question[rand];
      question[rand] = question[9];
      question[9] = tmp;
    }
    //System.out.println(question[0]+""+question[1]+""+question[2]+""+question[3]); //答えの出力
  }

  //問題の配列のゲッター
  public int getQestion(int i){
    return question[i];
  } 
} 

final class Input {
  private int input;//プレイヤーの答えの入力用　　
  private int num;//入力数値の桁格納用
  private int flag = 0 ;//プレイターの答えの重複チェックフラグ、flag = 1 重複あり
  
  //プレイヤーの答えの標準入力
    public Input() {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      try {
        String buf = br.readLine();
        input = Integer.parseInt(buf);
        String str = String.valueOf(buf);
        num = str.length();
      } catch (Exception e) {
        System.out.println("入力は数値のみです。");
        flag = 1;
      }
    }
    //プレイヤーの答えのゲッター
    public int getInput() {
      return input;
    }
    //重複フラグのセッター
    public void flagSetter(int i){
      flag = i;
    }
    //重複フラグのゲッター
    public int flagGetter() {
      return flag;
    }
    //入力数値の桁のゲッター
    public int lengthGetter() {
      return num;
    }
} 

//入力した通知を配列にセットし同じ数値の重複チェック
final class AnsSetter {
  private final int [] ans = new int[4];
    public AnsSetter(Input input){
      ans[3]=input.getInput()%10;
      ans[2]=input.getInput()/10%10;
      ans[1]=input.getInput()/10/10%10;
      ans[0]=input.getInput()/10/10/10%10;
    
      for (int i = 0;i < 4;i++) {
        for (int j = 0;j < 4;j++) {
          if (i == j) {

          } else {
            if (ans[i] == ans[j] ) {
            System.out.println("入力に重複があります");
            input.flagSetter(1);
            break;
            }
          }
        }
        if (input.flagGetter() == 1) {
          break;
        }
      }
    }
    //プレイヤーの答えのゲッター
    public int ansGet (int i){
      return ans[i];
    }
}

//hitとblowの判定
final class Ans_check{
  private int hit = 0;//hit数の格納
  private int blow = 0;//blow数の格納
  private int count2 = 0;//hit数の格納
  public Ans_check(int flag, int count, AnsSetter ans, Question q) {
    count2 = count;
    if (flag == 0) {        
      for (int i = 0;i < 4; i++) {
        for (int j = 0;j < 4; j++) {
          if (i == j) { //hit check                
            if (q.getQestion(i) == ans.ansGet(j)) {
              hit++;
            } else {  
              
            }
          } else { //blow check
            if (q.getQestion(i) == ans.ansGet(j)) {
              blow++;
            } else {  
                
            }
          }
        }
      }

      if (hit == 4) {
        System.out.println("正解！");
      } else {
        System.out.println("hit="+hit+",blow="+blow);
      }
      count2 = count;
      count2++;
    }
  }
  //hit数のゲッター
  public int hitGetter() {
      return hit;
  }
  //プレイヤーの試行回数のゲッター
  public int countGetter() {
      return count2;
  }
}

public class hitandblow2 { 
  static public void main (String[] args) throws IOException {
  Question q = new Question();
  int count = 1;//ゲームの試行回数の格納
  	while (true) {
  		System.out.println("4桁の数値を入力してください。(" + count +"回目)");
      Input input = new Input(); 
        
      if (input.flagGetter() == 0) {        
        if (input.lengthGetter() != 4) {
          System.out.println("入力が4桁ではありません");
          continue;
        }
        AnsSetter ans = new AnsSetter(input);
        Ans_check check = new Ans_check(input.flagGetter(),count,ans,q);
        count = check.countGetter();
        if (check.hitGetter() == 4) {
          return;
        }
      }
  	}    
  }
}