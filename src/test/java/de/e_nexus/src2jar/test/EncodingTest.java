   /   *           _   _   _           _   _   _           _               _       _       _   _       _   _   _       _   _   _           |   >   |   _       _   _   _                   _       _   _       _               _       _       _   _           _       _   _               _   _       _   |   >   |   _   _       |   >   |       _   _   _       (   >   )       _   _       _       _       _   _      
       *       /       _   _   |   /       _       \   |       |       |       |       '   _   _   /       _   _   /       _       \       |       _   _   /       _       \           |       '   _   _   |       |       |       |       '   _       \   |       '   _       \       /       _   `       |       '   _       \   |       |   /       _       \   |       |   /       _   `       |       '   _   _   |      
       *       \   _   _       \       (   _   )       |       |   _   |       |       |       |       (   _   |           _   _   /       |       |   |       (   _   )       |       |       |           |       |   _   |       |       |       |       |       |       |       |       (   _   |       |       |   _   )       |       |           _   _   /   |       |       (   _   |       |       |      
       *       |   _   _   _   /   \   _   _   _   /       \   _   _   ,   _   |   _   |           \   _   _   _   \   _   _   _   |           \   _   _   \   _   _   _   /           |   _   |               \   _   _   ,   _   |   _   |       |   _   |   _   |       |   _   |   \   _   _   ,   _   |   _   .   _   _   /   |   _   |   \   _   _   _   |   /       |   \   _   _   ,   _   |   _   |      
       *       C   o   p   y   r   i   g   h   t       P   e   t   e   r       R   a   d   e   r       2   0   1   8       -       G   N   U       3   .   0                                                                                                                                                                                               |   _   _   /      
       *   /      
   p   a   c   k   a   g   e       d   e   .   e   _   n   e   x   u   s   .   s   r   c   2   j   a   r   .   t   e   s   t   ;      
      
   i   m   p   o   r   t       j   a   v   a   .   i   o   .   F   i   l   e   ;      
   i   m   p   o   r   t       j   a   v   a   .   i   o   .   F   i   l   e   O   u   t   p   u   t   S   t   r   e   a   m   ;      
   i   m   p   o   r   t       j   a   v   a   .   n   e   t   .   U   R   L   ;      
   i   m   p   o   r   t       j   a   v   a   .   n   e   t   .   U   R   L   C   l   a   s   s   L   o   a   d   e   r   ;      
   i   m   p   o   r   t       j   a   v   a   .   u   t   i   l   .   z   i   p   .   Z   i   p   E   n   t   r   y   ;      
      
   i   m   p   o   r   t       d   e   .   e   _   n   e   x   u   s   .   s   r   c   2   j   a   r   .   s   t   r   e   a   m   .   C   o   m   p   i   l   e   J   a   r   O   u   t   p   u   t   S   t   r   e   a   m   ;      
      
   p   u   b   l   i   c       c   l   a   s   s       E   n   c   o   d   i   n   g   T   e   s   t       {      
   	   p   u   b   l   i   c       v   o   i   d       t   e   s   t   U   t   f   8   (   )       t   h   r   o   w   s       E   x   c   e   p   t   i   o   n       {      
   	   	   F   i   l   e       t   m   p   J   a   r       =       F   i   l   e   .   c   r   e   a   t   e   T   e   m   p   F   i   l   e   (   "   s   2   j   "   ,       "   t   e   s   t   .   j   a   r   "   )   ;      
      
   	   	   C   o   m   p   i   l   e   J   a   r   O   u   t   p   u   t   S   t   r   e   a   m       j   o   s       =       n   e   w       C   o   m   p   i   l   e   J   a   r   O   u   t   p   u   t   S   t   r   e   a   m   (   n   e   w       F   i   l   e   O   u   t   p   u   t   S   t   r   e   a   m   (   t   m   p   J   a   r   )   )   ;      
   	   	   j   o   s   .   p   u   t   N   e   x   t   E   n   t   r   y   (   n   e   w       Z   i   p   E   n   t   r   y   (   "   d   e   /   T   e   s   t   .   j   a   v   a   "   )   )   ;      
   	   	   j   o   s   .   w   r   i   t   e   (      
   	   	   	   	   "   p   a   c   k   a   g   e       d   e   ;       p   u   b   l   i   c       c   l   a   s   s       T   e   s   t   {   p   u   b   l   i   c       S   t   r   i   n   g       t   e   s   t   (   )   {   r   e   t   u   r   n       n   e   w       S   t   r   i   n   g   (   n   e   w       b   y   t   e   [   ]       {       (   b   y   t   e   )       0   x   F   4   ,       (   b   y   t   e   )       0   x   8   F   ,       (   b   y   t   e   )       0   x   B   F   ,       (   b   y   t   e   )       0   x   B   D       }   ,       j   a   v   a   .   n   i   o   .   c   h   a   r   s   e   t   .   C   h   a   r   s   e   t   .   f   o   r   N   a   m   e   (   \   "   u   t   f   3   2   \   "   )   )   ;   }   }   "   .   g   e   t   B   y   t   e   s   (   )   )   ;      
   	   	   j   o   s   .   c   l   o   s   e   E   n   t   r   y   (   )   ;      
   	   	   j   o   s   .   c   l   o   s   e   (   )   ;      
   	   	   U   R   L   C   l   a   s   s   L   o   a   d   e   r       c   l       =       U   R   L   C   l   a   s   s   L   o   a   d   e   r   .   n   e   w   I   n   s   t   a   n   c   e   (   n   e   w       U   R   L   [   ]       {       t   m   p   J   a   r   .   t   o   U   R   L   (   )       }   )   ;      
   	   	   O   b   j   e   c   t       k       =       c   l   .   l   o   a   d   C   l   a   s   s   (   "   d   e   .   T   e   s   t   "   )   .   n   e   w   I   n   s   t   a   n   c   e   (   )   ;      
   	   	   S   t   r   i   n   g       n       =       (   S   t   r   i   n   g   )       k   .   g   e   t   C   l   a   s   s   (   )   .   g   e   t   M   e   t   h   o   d   (   "   t   e   s   t   "   )   .   i   n   v   o   k   e   (   k   )   ;      
   	   	   a   s   s   e   r   t       n   .   e   q   u   a   l   s   (   "  ��   "   )   ;      
   	   }      
   }