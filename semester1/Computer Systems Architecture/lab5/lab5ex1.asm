bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    s db 1,2,3,4 ; declare the string of bytes
	l equ $-s ; compute the length of the string in l
	d times l-1 dw 0 ; reserve l bytes for the destination string and initialize it 

; our code starts here
segment code use32 class=code
    start:
        ; 1.Given a byte string S of length l, obtain the string D of length l-1 as D(i) = S(i) * S(i+1) (each element of D is the product of two consecutive elements of S).
    ;Example:
    ;S: 1, 2, 3, 4
    ;D: 2, 6, 12
        mov ecx, l-1 ; we put the length l in ECX in order to make the loop
        mov esi, 0     
        jecxz Sfarsit      
        Repeta:
            mov al, [s+esi]
            mov bl, [s+esi+1] 
            mul bl          
            mov [d+esi*2], ax   
            inc esi
        loop Repeta
        Sfarsit:;end of the program
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
