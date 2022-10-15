package org.cloud.federation.utils;

import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.filter.*;
import java.util.List;
import java.util.Iterator;

public class JDOM2
{
   static org.jdom.Document document;
   static Element racine;

   public static void main(String[] args)
   {
      //On crée une instance de SAXBuilder
      SAXBuilder sxb = new SAXBuilder();
      try
      {
         //On crée un nouveau document JDOM avec en argument le fichier XML
         //Le parsing est terminé ;)
         document = sxb.build(new File("/home/cloud/Desktop/Exercice2.xml"));
      }
      catch(Exception e){}

      //On initialise un nouvel élément racine avec l'élément racine du document.
      racine = document.getRootElement();

      //Méthode définie dans la partie 3.2. de cet article
      List listEtudiants = racine.getChildren("etudiant");

      //On crée un Iterator sur notre liste
      Iterator i = listEtudiants.iterator();
      while(i.hasNext())
      {
         //On recrée l'Element courant à chaque tour de boucle afin de
         //pouvoir utiliser les méthodes propres aux Element comme :
         //sélectionner un nœud fils, modifier du texte, etc...
         Element courant = (Element)i.next();
         //On affiche le nom de l’élément courant
         System.out.println(courant.getChild("nom").getText());
      }
   }
}

