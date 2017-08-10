JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        PriorityQueue_Node.java \
        Binary_Heap.java \
        FourWayOptimalHeap.java \
        Pairing_Heap.java \
        HuffmanTree.java \
	encoder.java \
	decoder.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
