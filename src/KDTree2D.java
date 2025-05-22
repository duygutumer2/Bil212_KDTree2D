import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class KDTree2D {

    private static class Node{

        private Node left;
        private Node right;
        private Node parent;
        private Point2D p;

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setP(Point2D p) {
            this.p = p;
        }

        public void setRight(Node right) {
            this.right = right;
        }
        public double getXValue(){
            return p.getX();
        }
        public double getYValue(){
            return p.getY();
        }

        public Point2D getP() {
            return p;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node(Point2D p,Node parent){
            this.p=p;
            left=null;
            right=null;
            this.parent=parent;
        }
        public boolean isExternal(){
            if(this.right==null&&this.left==null)return true;
            return false;
        }
    }

    private Node root;
    private int size;

    public KDTree2D(){
        root = null;
        size=0;
    }

    public Point2D insert(Point2D point){
        if(root==null){
            root = new Node(point,null);
            size++;
            return point;
        }
        else{
            return helperInsert(root,point,0);
        }


    }

    private Point2D helperInsert(Node p,Point2D point,int d){

        if(p.getP().equals(point))return null;

        if(d%2==0){
            if (point.getX() < p.getXValue()) {
                if (p.left == null) {
                    p.setLeft(new Node(point,p));

                    size++;
                    return point;
                }
                else {
                    return helperInsert(p.left, point, d + 1);
                }
            }
            else{
                if(p.right==null){
                    p.setRight(new Node(point,p));
                    size++;
                    return point;
                }
                else{
                   return helperInsert(p.right,point,d+1);
                }

            }

        }
        else{
            if (point.getY() < p.getYValue()) {
                if (p.left == null) {
                    p.setLeft(new Node(point,p));
                    size++;
                    return point;
                }
                else {
                    return helperInsert(p.left, point, d + 1);
                }
            }
            else{
                if(p.right==null){
                    p.setRight(new Node(point,p));
                    size++;
                    return point;
                }
                else{
                   return helperInsert(p.right,point,d+1);
                }

            }
        }


    }
    public Point2D search(Point2D point){

       return helperSearch(this.root,point,0);
    }
    private Point2D helperSearch(Node n,Point2D point,int d){

        if(n==null)return null;
        //if(n.isExternal())return point;

        if(n.getP().equals(point))return point;

        if(d%2==0){
            if(point.getX()<n.getXValue())
                return helperSearch(n.left,point,d+1);
            else
                return helperSearch(n.right,point,d+1);
        }
        else{
            if(point.getY()<n.getYValue())
                return helperSearch(n.left,point,d+1);
            else
                return helperSearch(n.right,point,d+1);
        }



    }

    public void remove(Point2D point){

        if(size==0)return;

        helperRemove(this.root,point,0);
        size--;
    }
    private void helperRemove(Node n,Point2D p,int d){
        if(n==null)return ;

        Node node = findNode(n,p,d);//silinmek istenen node bulunur.

        if(node==null)return;

        if(node.isExternal()) { // silinmek istenen node external ise parentını null'a bağla
           if(node.getParent()!=null){
               Node parent = node.getParent();
               if(parent.left==node){
                   parent.left=null;
               }
               else{
                   parent.right=null;
               }
           }
           else{ //silinmek istenen node'un parent'ı null ise silinmek istenen root'dur.
               root=null;
           }
        }
        if(node.left!=null && node.right==null){//sadece left çocuk varsa
            if(node.getParent()!=null){
                Node parent = node.getParent();
                if(parent.left==node){//node parent'ın left'i ise  parent.left node.left'e eşitlenir.
                    parent.left=node.left;
                }
                else{
                    parent.right=node.left;
                }
                node.left.setParent(parent);
            }
            else{//silinmek istenen root dur.
                root=node.left;
                root.setParent(null);
            }



        }
        else if(n.right!=null && n.left==null){ // right çocuk ise yukarıdaki if'in aynı mantık çalışır.
            if(node.getParent()!=null){
                Node parent = node.getParent();
                if(parent.right==node){
                    parent.right=node.right;
                }
                else{
                    parent.left=node.right;
                }
                node.right.setParent(parent);
            }
            else{//silinmek istenen root dur.
                root=node.right;
                root.setParent(null);
            }
        }
        else{ // eğer iki çocuğuda var ise

            if(d%2==0){
                Point2D m = helperFindMin(node.right,1,0);//ilk önce sonraki gelen leveldaki yani y deki min y yi bulmalıyız.
                node.setP(m); //node'un value su minimuma set edilir ve recursive minimum silinir.
                helperRemove(node.right,m,d+1);

            }
            else{
                Point2D m = helperFindMin(node.right,0,0);
                node.setP(m);
                helperRemove(node.right,m,d+1);
            }

        }

    }
    private Node findNode(Node n,Point2D p,int d){
        if(n==null)return null;
        if(n.getP().equals(p))return n;

        if (d % 2 == 0) {
            if (p.getX() < n.getXValue()) {
                return findNode(n.left, p, d + 1);
            } else {
                return findNode(n.right, p, d + 1);
            }
        } else {
            if (p.getY() < n.getYValue()) {
                return findNode(n.left, p, d + 1);
            } else {
                return findNode(n.right, p, d + 1);
            }
        }
    }
    public void displayTree(){

        helperDisplay(this.root,0);
    }
    private void helperDisplay(Node n,int d){

        if(n==null)return;

        for(int i=0;i<d;i++)
            System.out.print(". ");

        System.out.println("("+n.getXValue()+", "+n.getYValue()+")");
        helperDisplay(n.getLeft(),d+1);
        helperDisplay(n.getRight(),d+1);


    }
    private void swap(Node n1, Node n2) {
        Point2D temp = n1.getP();
        n1.setP(n2.getP());
        n2.setP(temp);
    }

     Point2D findMin(int d){

        if(d<0)throw new IllegalArgumentException();
        return helperFindMin(this.root,d,0);

    }
    private Point2D helperFindMin(Node n,int d,int l){

        if(n==null)return null;

        Point2D min = n.getP();
        if (l % 2 != d) {//eğer şuanki level istediğimiz dimension değilse aşağı in orda ara
            Point2D left = helperFindMin(n.getLeft(), d, l + 1);
            Point2D right = helperFindMin(n.getRight(), d, l + 1);

            if (d == 0) {
                if (left != null && left.getX() < min.getX()) {
                    min = left;
                }
                if (right != null && right.getX() < min.getX()) {
                    min = right;
                }
            } else {
                if (left != null && left.getY() < min.getY()) {
                    min = left;
                }
                if (right != null && right.getY()<min.getY()) {
                    min = right;
                }
            }
        }
        else {//istenilen dimension ise

            if (d == 0) {

                Point2D left = helperFindMin(n.getLeft(), 0,l+1);
                Point2D right = helperFindMin(n.getRight(), 0,l+1);
                if (left != null && left.getX() < min.getX())
                    min = left;
                else if (right != null && right.getX() < min.getX())
                    min = right;
            } else if (d == 1) {
                Point2D left = helperFindMin(n.getLeft(), 1,l+1);
                Point2D right = helperFindMin(n.getRight(), 1,l+1);
                if (left != null && left.getY() < min.getY())
                    min = left;
                else if (right != null && right.getY() < min.getY())
                    min = right;
            }
        }
         return min;
    }

     Point2D findMax(int d){
         if(d<0)throw new IllegalArgumentException();
         return helperFindMax(this.root,d,0);
     }

     private Point2D helperFindMax(Node n,int d,int l){
         if(n==null)return null;

         Point2D max = n.getP();
         if (l % 2 != d) {
             Point2D left = helperFindMax(n.getLeft(), d, l + 1);
             Point2D right = helperFindMax(n.getRight(), d, l + 1);

             if (d == 0) {
                 if (left != null && left.getX() > max.getX()) {
                     max = left;
                 }
                 if (right != null && right.getX() > max.getX()) {
                     max = right;
                 }
             } else {
                 if (left != null && left.getY() > max.getY()) {
                     max = left;
                 }
                 if (right != null && right.getY() > max.getY()) {
                     max = right;
                 }
             }
         }
         else {

             if (d == 0) {

                 Point2D left = helperFindMax(n.getLeft(), 0, l + 1);
                 Point2D right = helperFindMax(n.getRight(), 0, l + 1);
                 if (left != null && left.getX() > max.getX())
                     max = left;
                 else if (right != null && right.getX() > max.getX())
                     max = right;
             } else if (d == 1) {
                 Point2D left = helperFindMax(n.getLeft(), 1, l + 1);
                 Point2D right = helperFindMax(n.getRight(), 1, l + 1);
                 if (left != null && left.getY() > max.getY())
                     max = left;
                 else if (right != null && right.getY() > max.getY())
                     max = right;
             }
         }
         return max;
     }





    Iterable<Point2D> printRectangularRange(Point2D ll, Point2D ur){

        List<Point2D> list = new ArrayList<>();
        helperRectangular(root,ll,ur,list,0);
        return list;
    }
    private void helperRectangular(Node n,Point2D ll,Point2D ur, List<Point2D> list,int d){

        if(n==null)return;
        Point2D curr = n.getP();
        if(curr.getX()>=ll.getX() && curr.getY()>=ll.getY() && curr.getX()<=ur.getX()&&curr.getY()<=ur.getY()){ // içindemi diye bakıyoruz
            list.add(curr);

        }

        if(d%2==0){
            if(curr.getX()>=ll.getX()){ //eğer d=0 ise şuanki node left cornerın x inden büyükse lefti gez değilse gezmene gerek yok
                helperRectangular(n.left,ll,ur,list,d+1);
            }
            if(ur.getX()>=curr.getX())
                helperRectangular(n.right,ll,ur,list,d+1);
        }
        else{
            if(curr.getY()>=ll.getY()){ //eğer d=0 ise şuanki node left cornerın x inden büyükse lefti gez değilse gezmene gerek yok
                helperRectangular(n.left,ll,ur,list,d+1);
            }
            if(ur.getY()>=curr.getY())
                helperRectangular(n.right,ll,ur,list,d+1);
        }
    }

    Iterable<Point2D> printCircularRange(Point2D c, double r){
        if(r<0){
           throw new IllegalArgumentException();
        }
        List<Point2D> list = new ArrayList<>();
        helperCircular(root,c,r,list,0);
        return list;
    }

    private void helperCircular(Node n,Point2D c,double r,List<Point2D> list,int d){
        if(n==null)return;
        Point2D curr = n.getP();
        if(Math.sqrt(Math.pow(curr.getX()-c.getX(),2)+Math.pow(curr.getY()-c.getY(),2))<=r){//(sqrt(x1-x2)^2+(y1-y2)^2)<=yarıçap
            list.add(curr);

        }

        if(d%2==0){//x
            if(c.getX()-r<=curr.getX()){//x'e göre ayırdığında hala dairenin içinde mi en uç soldan x noktası ile şuanki noktanın x i karşılaştırılıyor.eğer şuanki nokta içindeyse diğer sol subdakilerde içinde olabilir.
                helperCircular(n.left,c,r,list,d+1);
            }
            if(curr.getX()<=c.getX()+r)//sağdan en uç x ile karşılaştırma
                helperCircular(n.right,c,r,list,d+1);
        }
        else{//y
            if(curr.getY()>=c.getY()-r){//y ekseni bounca aşağıdan en uç nokta
                helperCircular(n.left,c,r,list,d+1);
            }
            if(curr.getY()<=c.getY()+r)//yukardan en uç nokta ile
                helperCircular(n.right,c,r,list,d+1);
        }

        }
    }





