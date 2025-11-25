#type vertex
#version 440 core

in vec3 position;
in vec2 texcoord;
in vec3 normal;
out vec2 passTexcoord;
out vec3 passNormal;
out vec3 passLightPosition;
uniform mat4 transformation;
uniform mat4 view;
uniform mat4 projection;
uniform vec3 lightPosition;

void main() {
    vec4 worldPosition = transformation * vec4(position, 1.0F);

    gl_Position = projection * view * worldPosition;
    passTexcoord = texcoord;
    passNormal = transpose(inverse(mat3(transformation))) * normal;
    passLightPosition = lightPosition - worldPosition.xyz;
}

#type fragment
#version 440 core

in vec2 passTexcoord;
in vec3 passNormal;
in vec3 passLightPosition;
out vec4 passColor;
uniform vec4 ambientColor;
uniform vec4 diffuseColor;
uniform vec4 specularColor;
uniform bool textured;
uniform sampler2D txr;
uniform vec4 lightColor;

void main() {
    vec3 normal = normalize(passNormal);
    vec3 lightPosition = normalize(passLightPosition);
    float ndotl = dot(normal, lightPosition);
    float brightness = max(ndotl, 0.1F);
    vec4 color = brightness * lightColor;

    if (textured) {
        passColor = color * texture(txr, passTexcoord);
    } else {
        passColor = color * (ambientColor + diffuseColor);
    }
}