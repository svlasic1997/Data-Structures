package edu.iastate.cs228.proj1;

/*
 * Example code
*/

import java.util.Arrays; 

public class BioSeqData
{
  public static void main(String[] args)
  {
    System.out.println( "A first example");
    String demodna = new String("AATGCCAGTCAGCATAGCGTAGACT");
    int[] ardemo = {1, 5, 8, 10, 13, 16};
    GenomicDNASequence gdemo = new GenomicDNASequence(demodna.toCharArray());
    System.out.println( "Genomic sequence: " + gdemo );
    System.out.println( "Array of exon positions: " + Arrays.toString( ardemo ) );
    gdemo.markCoding(1, 16);
    CodingDNASequence cdemo = new CodingDNASequence( gdemo.extractExons(ardemo) );
    System.out.println( "Coding sequence: " + cdemo );
    if ( cdemo.checkStartCodon() )
    {
      System.out.println( "It has a start codon");
      ProteinSequence aademo = new ProteinSequence( cdemo.translate() );
      System.out.println(  "Protein sequence: " + aademo );
    }

    System.out.println( "");
    System.out.println( "A second example");
    String dnastr = new String("CTTCCATGCCGCCACCGCTGCAGATTGACGACGTCGTCTACGAAACTCAGATGGACAAGGC");
    GenomicDNASequence gseq = new GenomicDNASequence(dnastr.toCharArray());
    System.out.println( "Genomic sequence: " + gseq );
    int[] parr = {5, 20, 30, 50};
    System.out.println( "Array of exon positions: " + Arrays.toString( parr ) );
    gseq.markCoding(5, 50);
    CodingDNASequence cdsseq = new CodingDNASequence( gseq.extractExons(parr) );
    System.out.println( "Coding sequence: " + cdsseq );
    if ( cdsseq.checkStartCodon() )
    {
      System.out.println( "It has a start codon");
      ProteinSequence aaseq = new ProteinSequence( cdsseq.translate() );
      System.out.println( "Protein sequence: " + aaseq );
    }

//  String probst = new String("T$G");
//  Sequence seqobj = new Sequence( probst.toCharArray() );

//  String probst2 = new String("TDG");
//  DNASequence dnaseqobj = new DNASequence( probst2.toCharArray() );

//  String probst3 = new String("TGCH");
//  GenomicDNASequence gdnaobj = new GenomicDNASequence( probst3.toCharArray());

//  String probst4 = new String("BJU");
//  ProteinSequence probj = new ProteinSequence( probst4.toCharArray());
  }
}
