bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a dw 1
    b db 1
    c dw 1
    d db 1
    e dd 3
    x dq 3
    

; our code starts here
segment code use32 class=code
    start:
        ;16.x/2+100*(a+b)-3/(c+d)+e*e; a,c-word; b,d-byte; e-doubleword; x-qword  unsigned
        mov eax, dword [x+0] 
	    mov edx, dword [x+4] 
        mov ebx,2
        div ebx; eax: x/2
        mov ebx,eax  ;ebx=x/2
        mov cx,[a]
        mov dl,[b]
        mov dh,0
        add cx,dx ; cx:a+b
        mov ax,100
        mul cx; dx:ax= 100*(a+b)
        push dx
        push ax
        pop eax ; eax=100*(a+b)
        add eax,ebx; eax=x/2+ 100*(a+b)
        push eax
        mov eax,3
        push eax
        pop ax
        pop dx ; dx:ax=3
        mov bl,[d]
        mov bh,0
        add bx,[c]; bx:c+d
        div bx; ax=3/(c+d)
        mov bx,ax; bx=3/(c+d)
        mov cx,0
        push cx
        push bx
        pop ebx
        pop eax
        sub eax,ebx; eax=x/2+100*(a+b)-3/(c+d)
        mov ebx,eax;ebx=x/2+100*(a+b)-3/(c+d)
        mov eax,[e]
        mul eax; edx:eax=e*e
        mov ecx,0
        add eax, ebx  ; eax=  eax+ebx 
        add edx, ecx ; edx:eax=x/2+100*(a+b)-3/(c+d)+e*e
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
