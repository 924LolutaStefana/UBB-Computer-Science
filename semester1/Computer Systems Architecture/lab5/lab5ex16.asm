bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    s1 db  'a', 'b', 'c', 'b', 'e', 'f'
     l equ $-s1
    s2 db '1', '2', '3', '4', '5'
    ; compute the length of the string in l
	d times l db 0;
  
; our code starts here
segment code use32 class=code
    start:
        ;Two character strings S1 and S2 are given. Obtain the string D by concatenating the elements found on odd positions in S2 and the elements found on even positions in S1.
;Example:
;S1: 'a', 'b', 'c', 'b', 'e', 'f'
;S2: '1', '2', '3', '4', '5'
;D: '1', 'b', '3', 'b', '5', 'f'
        mov eax,0
        mov ebx,0 
        mov ecx,0 
        mov edx,0
        mov ecx, l ; we put the length l in ECX in order to make the loop
        mov esi, 0     
        mov dx,1
        jecxz Sfarsit      
        Repeta:
        
            mov ax,dx
            mov bl,2
            div bl
            cmp ah,0
            je poz_par
            jmp poz_impar
            poz_par:
                mov al, [s1+esi]
                mov [d+esi],al
                jmp end_cond
            poz_impar:
                mov al, [s2+esi]
                mov [d+esi],al
                
            end_cond:
                inc esi
                add dx,1
        loop Repeta
        Sfarsit:;end of the program
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
