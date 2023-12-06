bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a dw 0111_0111_0101_0111b
     b dw 1001_1011_1011_1110b
     c dd 0

; our code starts here
segment code use32 class=code
    start:
        ;16.Given the byte A and the word B, compute the doubleword C as follows:
    ;the bits 0-7 of C have the value 1
    ;the bits 8-11 of C are the same as the bits 4-7 of A
    ;the bits 12-19 are the same as the bits 2-9 of B
    ;the bits 20-23 are the same as the bits 0-3 of A
    ;the bits 24-31 are the same as the high byte of B
    
        mov ebx,0
        or   bx, 0000_0000_1111_1111b 
        
        mov  ax, [a] ; we isolate bits 4-7 of A
        and  ax, 0000_0000_1111_0000b
        mov  cl, 4
        rol  ax, cl ; we rotate 4 positions to the left
        or   bx, ax ; the bits 8-11 of C are the same as the bits 4-7 of A
        
        
        mov eax,0
        mov  ax, [b] 
        and  ax, 0000_0011_1111_1100b
        mov  cl, 10
        rol  eax, cl ; we rotate 10 positions to the left
        or   ebx, eax ; the bits 12-19 of C are the same as the bits 2-9 of B
        
         mov eax,0
        mov  ax, [a] 
        and  ax, 0000_0000_0000_1111b
        mov  cl, 20
        rol  eax, cl ; we rotate 29 positions to theleft
        or   ebx, eax ; the bits 20-23 are the same as the bits 0-3 of A
        
        ;8-15
        
         mov eax,0
        mov  ax, [b] 
        and  ax, 1111_1111_0000_0000b
        mov  cl, 16
        rol  eax, cl ; we rotate 16 positions to the left
        or   ebx, eax ;24-31 are the same as the high byte of B
        
        mov [c],ebx
        
 
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
