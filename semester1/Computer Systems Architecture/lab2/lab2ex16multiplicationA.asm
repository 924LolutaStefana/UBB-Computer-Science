bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db 2
    b db 4
    c db 2
    doi db 2

; our code starts here
segment code use32 class=code
    start:
        ; a,b,c - byte, d - word      16.(a+b)/2 + (10-a/c)+b/4
        mov al,[a];        unsigned conversion from byte to word
        mov ah,0
        add al,[b]
        div byte[doi]; al: (a+b)/2
        mov bl,al ; bl:(a+b)/2
        mov al,[a]
        mov ah,0
        div byte[c] ; al:a/c
        mov cl,10
        sub cl,al ; cl:10-a/c
        add bl,cl ;bl: (a+b)/2 + (10-a/c)
        mov al,[b]
        mov ah,0
        mov cl,4
        div cl; al:b/4
        add al,bl ; al: (a+b)/2 + (10-a/c)+b/4
        
        
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
