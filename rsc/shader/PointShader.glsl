#type vertex
#version 440 core

in vec3 position;

uniform mat4 view;
uniform mat4 projection;

void main() {
    mat4 rotView = view;

    rotView[3] = vec4(0.0F, 0.0F, 0.0F, 1.0F);

    gl_Position = projection * rotView * vec4(position, 1.0F);
    gl_PointSize = 2.0F;
}

#type fragment
#version 440 core

out vec4 passColor;

void main() {
    passColor = vec4(1.0F, 1.0F, 1.0F, 1.0F);
}