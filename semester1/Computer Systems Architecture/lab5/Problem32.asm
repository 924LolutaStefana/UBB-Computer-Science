;A byte string S of length l is given. Obtain the string D of length l-1, such that each element of D is the quotient of two consecutive ;elements of S, i.e. D(i) = S(i) / S(i+1).
;Example:
;S: 1, 6, 3, 1
;D: 0, 2, 3
bits 32 
global start        
extern exit ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll ; exit is a function that ends the calling process. It is defined in msvcrt.dll

segment data use32 class=data
	s db 1,6,3,1 ; declare the string of bytes
	l equ $-s ; compute the length of the string in l
	d times l-1 db 0 ; reserve l bytes for the destination string and initialize it
segment code use32 class=code
start:
	mov ecx, l-1 ; we put the length l-1 in ECX in order to make the loop
	mov esi, 0     
	jecxz Sfarsit      
	Repeta:
        
		mov al, [s+esi]
        cbw
        mov bl, [s+esi+1]
        idiv bl            
		mov [d+esi], al    
		inc esi
	loop Repeta
	Sfarsit:

	
	push dword 0
	call [exit] 